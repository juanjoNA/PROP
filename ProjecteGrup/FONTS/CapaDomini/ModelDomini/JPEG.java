package CapaDomini.ModelDomini;

import Excepcions.DatosIncorrectos;
import Excepcions.VersionPPMIncorrecta;
import java.io.*;
import java.util.ArrayList;

public class JPEG {

    int[][] QcTable = new int[][] {
            {1, 18, 24, 47, 99, 99, 99, 99},
            {18, 21, 26, 66, 99, 99, 99, 99},
            {24, 26, 56, 99, 99, 99, 99, 99},
            {47, 66, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99}
    };


    static double[] normalizingScale = new double[] {1/Math.sqrt(2),1,1,1,1,1,1,1};

    private int hsize;
    private int vsize;
    private int origVsize;
    private int origHsize;

    public JPEG () {

    }

    private float[][][] RGBToYCbCr(byte[] content) {
        float[][][] YCbCr = new float[3][vsize][hsize];
        for (int i = 0; i < origVsize; ++i) {
            for (int j = 0; j < origHsize; j++) {
                byte r = content[i*origHsize*3 + j*3];
                byte g = content[i*origHsize*3 + j*3 + 1];
                byte b = content[i*origHsize*3 + j*3 + 2];
                YCbCr[0][i][j] =        (0.299f     * r + 0.587f    * g + 0.114f    * b);
                YCbCr[1][i][j] = 128 +  (-0.168736f * r - 0.331264f * g + 0.5f      * b);
                YCbCr[2][i][j] = 128 +  (0.5f       * r - 0.418688f * g - 0.081312f * b);
            }
        }
        return YCbCr;
    }

    private byte[][][] YCbCrToRGB (float[][][] YCbCr) {
        byte[][][] newRGB = new byte[3][vsize][hsize];
        for (int i = 0; i < vsize; ++i) {
            for (int j = 0; j < hsize; ++j) {
                float y =  YCbCr[0][i][j];
                float cb = YCbCr[1][i][j] - 128;
                float cr = YCbCr[2][i][j] - 128 ;
                int r  = Math.round(y +                              1.402f * cr);
                int g = Math.round(y - 0.34414f * cb - 0.71414f * cr);
                int b = Math.round(y + 1.772f     * cb                        );
                //Taking care that quantization did not make the value greater than the max value or less than the minimum value
                if (r < -128) newRGB[0][i][j] = -128;
                else if (r > 127) newRGB[0][i][j] = 127;
                else newRGB[0][i][j] = (byte)r;

                if (g < -128) newRGB[1][i][j] = -128;
                else if (g > 127) newRGB[1][i][j] = 127;
                else newRGB[1][i][j] = (byte)g;

                if (b < -128) newRGB[2][i][j] = -128;
                else if (b > 127) newRGB[2][i][j] = 127;
                else newRGB[2][i][j] = (byte)b;
            }
        }
        return newRGB;
    }

    private float[][][] subtract128(float[][][] matrix) {
        for (int i = 0; i < vsize; ++i) {
            for(int j = 0; j < hsize; ++j) {
                //matrix[0][i][j] -= 128;
                matrix[1][i][j] -= 128;
                matrix[2][i][j] -= 128;
            }
        }
        return matrix;
    }

    private float[][][] add128(float[][][] matrix) {
        for (int i = 0; i < vsize; ++i) {
            for(int j = 0; j < hsize; ++j) {
                //matrix[0][i][j] += 128;
                matrix[1][i][j] += 128;
                matrix[2][i][j] += 128;
            }
        }
        return matrix;
    }

    private float [][][] doDCT(float[][][] YCbCr,float[][][] actual,int a, int b) {
        for (int i = a; i < a+8; ++i) {
            for (int j = b; j < b+8; ++j) {
                actual[0][i][j] = YCbCr[0][i][j];
            }
        }
        double pi = Math.PI;
        for (int i = 1; i < 3; ++i) {
            for (int j = a; j < a+8; ++j) {
                for (int k = b;k < b+8; ++k) {
                    double sum = 0f;
                    for (int x = 0; x < 8; ++x) {
                        for (int y = 0; y < 8; ++y) {
                            sum +=   Math.cos((((2*x+1)*(j%8)*pi)/16))
                                    *Math.cos((((2*y+1)*(k%8)*pi)/16))
                                    *YCbCr[i][a+x][b+y];
                        }
                    }
                    actual[i][j][k] = (float)(sum * (normalizingScale[j%8]*normalizingScale[k%8])/4);
                }
            }
        }
        return actual;
    }

    private float [][][] undoDCT(float[][][] quantized, float[][][] actual,int a,int b) {
        for (int i = a; i < a+8; ++i) {
            for (int j = b; j < b+8; ++j) {
                actual[0][i][j] = quantized[0][i][j];
            }
        }
        double pi = Math.PI;
        for(int i = 1; i < 3; ++i) {
            for(int j = a; j < a+8; ++j) {
                for(int k = b; k < b+8; ++k) {
                    double sum = 0;
                    for (int x = 0; x < 8; ++x) {
                        for (int y = 0; y < 8; ++y) {
                            sum += (normalizingScale[x]*normalizingScale[y])
                                    *Math.cos(((2*(j%8)+1)*x*pi)/16)
                                    *Math.cos(((2*(k%8)+1)*y*pi)/16)
                                    *quantized[i][a+x][b+y];
                        }
                    }
                    actual[i][j][k] = (float)(sum/4.0);
                }
            }
        }
        return actual;
    }

    private int[][][] quantize(float[][][] matrix, int[][][] actual, int a, int b) {
        for(int i = a; i < a+8; ++i) {
            for (int j = b; j < b+8; ++j) {
                actual[0][i][j] = Math.round(matrix[0][i][j]); ///QyTable[i%8][j%8]
                actual[1][i][j] = Math.round(matrix[1][i][j]/QcTable[i%8][j%8]);
                actual[2][i][j] = Math.round(matrix[2][i][j]/QcTable[i%8][j%8]);
            }
        }
        return actual;
    }

    private float[][][] dequantize(int[][][] matrix) {
        float[][][] actual = new float[3][vsize][hsize];
        for(int i = 0; i < vsize; ++i) {
            for (int j = 0; j < hsize; ++j) {
                actual[0][i][j] = matrix[0][i][j];//*QyTable[i%8][j%8];
                actual[1][i][j] = matrix[1][i][j]*QcTable[i%8][j%8];
                actual[2][i][j] = matrix[2][i][j]*QcTable[i%8][j%8];
            }
        }
        return actual;
    }

    private ArrayList<Pair<Byte,Short>> runLengthEncode(int[][][] mat, int row, int col, ArrayList<Pair<Byte,Short>> result) {
        //Cb
        int x = row;
        int y = col;
        Boolean asc = true;
        byte cont = 0;
        ZigZag rlCb = new ZigZag(mat[1], row, col);
        ZigZag rlCr = new ZigZag(mat[2], row, col);
        while (!rlCb.isEnd()) {
            int num = rlCb.getNextNumber();
            if (num != 0) {
                Pair<Byte,Short> newPair = new Pair<Byte,Short>(cont,(short)num);
                result.add(newPair);
                cont = 0;
            }
            else {
                ++cont;
            }
        }
        cont = 0;
        Pair<Byte,Short> temp = new Pair<Byte,Short>((byte)0,(short)0);
        result.add(temp);
        //Cr
        while (!rlCr.isEnd()) {
            int num = rlCr.getNextNumber();
            if (num != 0) {
                Pair<Byte,Short> newPair = new Pair<Byte,Short>(cont,(short)num);
                result.add(newPair);
                cont = 0;
            }
            else {
                ++cont;
            }
        }
        Pair<Byte,Short> temp2 = new Pair<Byte,Short>((byte)0,(short)0);
        result.add(temp2);
        return result;
    }

    private int[][][] runLengthDecode(ArrayList<Pair<Byte,Short>> encoded, int[][][] decoded,int[][] luminance) {
        int matNum = 0;
        int index = 0;
        ZigZag[] rl = new ZigZag[2];
        rl[0] = new ZigZag(decoded[1],0,0);
        rl[1] = new ZigZag(decoded[2],0,0);
        while (index < encoded.size()) {
            Pair<Byte,Short> p = encoded.get(index);
            ++index;
            if (p.getFirst() == 0 && p.getSecond() == 0) {
                while (!rl[matNum].isEnd()) {
                    rl[matNum].writeNum(0);

                }
                rl[matNum].nextSquare();
                if(matNum == 1) matNum = 0;
                else matNum = 1;
            }
            else {
                while (p.getFirst() > 0) {
                    p.setFirst(((byte)(p.getFirst()-1)));
                    rl[matNum].writeNum(0);
                }
                rl[matNum].writeNum(p.getSecond());
            }
        }
        decoded[0] = luminance;
        decoded[1] = rl[0].getMatrix();
        decoded[2] = rl[1].getMatrix();
        return decoded;
    }

    public ImatgeComprimida comprimir(Imatge imatgeDescomprimida) throws IOException, VersionPPMIncorrecta, DatosIncorrectos {
        long start = System.currentTimeMillis();
        origVsize = imatgeDescomprimida.getSizeV();
        origHsize = imatgeDescomprimida.getSizeH();
        if (imatgeDescomprimida.getContingut().length != (origVsize * origHsize*3)) {
            throw new DatosIncorrectos();
        }
        if (origHsize%8 != 0) {
            hsize = origHsize + (8 - (origHsize % 8));
        }
        else hsize = origHsize;
        if (origVsize%8 != 0) {
            vsize = origVsize + (8 - (origVsize % 8));
        }
        else vsize = origVsize;

        float[][][] YCbCr = RGBToYCbCr(imatgeDescomprimida.getContingut());
        YCbCr = subtract128(YCbCr);
        float[][][] DCTed = new float[3][vsize][hsize];
        int[][][] quantized = new int[3][vsize][hsize];
        ArrayList<Pair<Byte,Short>> encoded = new ArrayList<Pair<Byte,Short>>();
        for (int i = 0; i < vsize; i += 8) {
            for (int j = 0; j < hsize; j += 8) {
                DCTed     = doDCT(YCbCr,DCTed,i,j);
                quantized = quantize(DCTed,quantized,i,j);
                encoded  = runLengthEncode(quantized,i,j, encoded);
            }
        }

        Huffman huffmanEncoder = new Huffman();
        byte[]huffmanEncoded = huffmanEncoder.huffmanEncode(encoded);

        byte[] resultContent = new byte[hsize*vsize + huffmanEncoded.length];

        for (int i = 0; i < vsize; ++i) {
            for (int j = 0; j < hsize; ++j) {
                resultContent[i*hsize + j] = (byte)quantized[0][i][j];
            }
        }
        for (int i = 0; i < huffmanEncoded.length; ++i) {
            resultContent[hsize*vsize + i] = huffmanEncoded[i];
        }
        long end = System.currentTimeMillis();
        String newPath = imatgeDescomprimida.getPath();
        newPath = newPath.replace("ppm","jimg");
        ImatgeComprimida imatgeComprimida = new ImatgeComprimida(newPath,resultContent, imatgeDescomprimida.getVersion(),origVsize, origHsize, imatgeDescomprimida.getMaxVal(), vsize, hsize, huffmanEncoder.getDecodingHashMap(),encoded.size());
        Estadistiques e = new Estadistiques(start,end,imatgeDescomprimida.getMida(),imatgeComprimida.getMida());
        imatgeComprimida.setEstadistiques(e);
        return imatgeComprimida;
    }

    public Imatge descomprimir(ImatgeComprimida imatgeComprimida) throws VersionPPMIncorrecta, ExtensionIncorrecta, ExtensionIncorrecta, DatosIncorrectos {
        long start = System.currentTimeMillis();
        vsize = imatgeComprimida.getModifiedSizeV();
        hsize = imatgeComprimida.getModifiedSizeH();
        origVsize = imatgeComprimida.getSizeV();
        origHsize = imatgeComprimida.getSizeH();

        byte[] encodedContent = imatgeComprimida.getContingut();
        if (encodedContent.length < vsize*hsize) {
            throw new DatosIncorrectos();
        }
        int[][] luminance = new int[vsize][hsize];
        for (int i = 0; i < vsize; ++i) {
            for (int j = 0; j < hsize; ++j) {
                luminance[i][j] = encodedContent[i*hsize + j];
            }
        }
        byte[] content = new byte[encodedContent.length-(vsize*hsize)];
        for (int i = 0; i <content.length; ++i) {
            content[i] =  encodedContent[hsize*vsize + i];
        }

        Huffman huffmanDecoder = new Huffman(imatgeComprimida.getDecoder());
        ArrayList<Pair<Byte,Short>> huffmanDecoded = new ArrayList<Pair<Byte,Short>>();
        try {
            huffmanDecoded = huffmanDecoder.huffmanDecode(content, imatgeComprimida.getNumPairs());
        }
        catch (Exception e) {
            throw new DatosIncorrectos();
        }

        int[][][] decoded = new int[3][vsize][hsize];
        decoded = runLengthDecode(huffmanDecoded,decoded,luminance);
        float[][][] unDCTed = new float[3][vsize][hsize];
        float[][][] unQuantized = new float[3][vsize][hsize];
        unQuantized = dequantize(decoded);
        for (int i = 0; i < vsize; i += 8) {
            for (int j = 0; j < hsize; j += 8) {
                unDCTed = undoDCT(unQuantized,unDCTed,i,j);
            }
        }

        unDCTed = add128(unDCTed);


        byte [][][] RGB = YCbCrToRGB(unDCTed);

        byte[] resultDecompressed = new byte[origHsize * origVsize * 3];
        for (int i = 0; i < origVsize; ++i) {
            for (int j = 0; j < origHsize; ++j) {
                resultDecompressed[i*origHsize*3 + j*3] = RGB[0][i][j];
                resultDecompressed[i*origHsize*3 + j*3 + 1] = RGB[1][i][j];
                resultDecompressed[i*origHsize*3 + j*3 + 2] = RGB[2][i][j];
            }
        }
        long end = System.currentTimeMillis();
        String newPath = imatgeComprimida.getPath();
        newPath = newPath.replace("jimg", "ppm");
        Imatge imatgeDescomprimida = new Imatge(newPath,resultDecompressed,imatgeComprimida.getVersion(),origVsize, origHsize,imatgeComprimida.getMaxVal());
        Estadistiques e = new Estadistiques(start,end,imatgeComprimida.getMida(),imatgeDescomprimida.getMida());
        imatgeDescomprimida.setEstadistiques(e);
        return imatgeDescomprimida;
    }



}
