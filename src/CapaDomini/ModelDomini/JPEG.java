package CapaDomini.ModelDomini;

import Excepcions.DatosIncorrectos;
import Excepcions.ExtensionIncorrecta;
import Excepcions.VersionPPMIncorrecta;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class JPEG {

    int[][] QcTable = new int[][] {
            {17, 18, 24, 47, 99, 99, 99, 99},
            {18, 21, 26, 66, 99, 99, 99, 99},
            {24, 26, 56, 99, 99, 99, 99, 99},
            {47, 66, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99}
    };

    int[][] QcTable2 = new int[][] {
            {16, 11, 10, 16, 24, 40, 51, 61},
            {12, 12, 14, 19, 26, 58, 60, 55},
            {14, 13, 16, 24, 40, 57, 69, 56},
            {14, 17, 22, 29, 51, 87, 80, 62},
            {18, 22, 37, 56, 68, 109, 103, 77},
            {24, 35, 55, 64, 81, 104, 113, 92},
            {49, 64, 78, 87, 103, 121, 120, 101},
            {82, 92, 95, 98, 112, 100, 103, 99}
    };


    private int hsize;
    private int vsize;
    private int origVsize;
    private int origHsize;
    private int ssVSize;
    private int ssHSize;

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
                YCbCr[1][i][j] = (-0.168736f * r - 0.331264f * g + 0.5f      * b);
                YCbCr[2][i][j] = (0.5f       * r - 0.418688f * g - 0.081312f * b);
                
            }
        }
        return YCbCr;
    }

    private byte[][][] YCbCrToRGB (float[][][] YCbCr) {
        byte[][][] newRGB = new byte[3][vsize][hsize];
        for (int i = 0; i < vsize; ++i) {
            for (int j = 0; j < hsize; ++j) {
                float y =  YCbCr[0][i][j];
                float cb = YCbCr[1][i][j];
                float cr = YCbCr[2][i][j] ;
                
                int r  = Math.round(y +                              1.402f * cr);
                int g = Math.round(y - 0.34414f * cb - 0.71414f * cr);
                int b = Math.round(y + 1.772f     * cb                        );
                //Taking care that quantization did not make the value greater than the max value or less than the minimum value
                if (r < -128) newRGB[0][i][j] = -128;
                else if (r > 127) newRGB[0][i][j] = 127;
                else newRGB[0][i][j] = (byte)(r);

                if (g < -128) newRGB[1][i][j] = -128;
                else if (g > 127) newRGB[1][i][j] = 127;
                else newRGB[1][i][j] = (byte)(g);

                if (b < -128) newRGB[2][i][j] = -128;
                else if (b > 127) newRGB[2][i][j] = 127;
                else newRGB[2][i][j] = (byte)(b);
                //System.out.println("b is : " + b);
            }
        }
        return newRGB;
    }
    
    float[][][] subsample(float[][][] mat,int[] subsampling) {
        float[][][] result = new float[3][ssVSize][ssHSize];
        result[0] = mat[0].clone();
        int subX = subsampling[0]/subsampling[1];
        int subY = 1;
        if (subsampling[2] == 0)  subY = 2;
        int maxV = 0;
         if (subsampling[2] == 0) {
            maxV = vsize / 2;
        }
        else {
            maxV = vsize;
        }
        double div1 = subsampling[0]/subsampling[1];
        int maxH =  (int) (hsize / (div1));
        int npixels = subX*subY;
        for (int i = 0; i < maxV; ++i) {
            for (int j = 0; j < maxH; ++j) {
                double sumCb = 0;
                double sumCr = 0;
                for (int u = 0; u < subY; ++u) {
                    for (int v = 0; v < subX; ++v) {
                        //System.out.println(i + " -- " + j +  " _-_ " + u + " -- " + v + " : " + i*subY+u + " -- "+ j*subX+v);
                        sumCb += mat[1][i*subY+u][j*subX+v];
                        sumCr += mat[2][i*subY+u][j*subX+v];
                    }
                }
                result[1][i][j] = (float) (sumCb/npixels);
                result[2][i][j] = (float) (sumCr/npixels);
            }
        }
        return result;
    }
    
    float[][][] unSubsample(float[][][] mat, int[] subsampling) {
        float[][][] result = new float[3][vsize][hsize];
        result[0] = mat[0].clone();
        int subX = subsampling[0]/subsampling[1];
        int subY = 1;
        if (subsampling[2] == 0)  subY = 2;
        
        int maxV = 0;
         if (subsampling[2] == 0) {
            maxV = vsize / 2;
        }
        else {
            maxV = vsize;
        }
        double div1 = subsampling[0]/subsampling[1];
        int maxH =  (int) (hsize / (div1));
        
        for (int i = 0; i < maxV; ++i) {
            for (int j = 0; j < maxH; ++j) {
                for (int u = 0; u < subY; ++u) {
                    for (int v = 0; v < subX; ++v) {
                        result[1][i*subY+u][j*subX+v] = mat[1][i][j];
                        result[2][i*subY+u][j*subX+v] = mat[2][i][j];
                    }
                }
            }
        }
        return result;
    }

    private float[][][] subtract128(float[][][] mat) {
        int tamx = mat.length;
        int tamy = mat[0].length;
        int tamz = mat[0][0].length;
        float[][][] temp = new float[tamx][tamy][tamz];
        temp[0] = mat[0].clone();
        for (int i = 0; i < ssVSize; ++i) {
            for(int j = 0; j < ssHSize; ++j) {
                temp[1][i][j] = mat[1][i][j] - 128;
                temp[2][i][j] = mat[2][i][j] - 128;
            }
        }
        return temp;
    }

    private float[][][] add128(float[][][] matrix) {
        int tamx = matrix.length;
        int tamy = matrix[0].length;
        int tamz = matrix[0][0].length;
        float[][][] temp = new float[tamx][tamy][tamz];
        temp[0] = matrix[0].clone();
        for (int i = 0; i < ssVSize; ++i) {
            for(int j = 0; j < ssHSize; ++j) {
                temp[1][i][j] = matrix[1][i][j] + 128;
                temp[2][i][j] = matrix[2][i][j] + 128;
            }
        }
        return temp;
    }
    
    static double[] normalizingScale = new double[] {1.0/Math.sqrt(2.0),1.0,1.0,1.0,1.0,1.0,1.0,1.0};

    private float [][][] doDCT(float[][][] YCbCr,float[][][] actual,int initialX, int initialY) {
        for (int i = 1; i < 3; ++i) {
            for (int u = 0; u < 8; ++u) {
                for (int v = 0; v < 8; ++v) {
                    double sum = 0.0;
                    for (int x = 0; x < 8; ++x) {
                        for (int y = 0; y < 8; ++y) {
                            sum +=   YCbCr[i][initialX + x][initialY + y] * 
                                            Math.cos((Math.PI * (2.0*x+1.0) *  u)/16.0) * 
                                            Math.cos((Math.PI * (2.0*y +1.0) * v)/16.0);
                        }
                    }
                    actual[i][initialX+u][initialY + v] = (float) ((2.0/8.0) * normalizingScale[u] * normalizingScale[v] *sum);
                }
            }
        }
        
        return actual;
    }

    private float [][][] undoDCT(float[][][] quantized, float[][][] actual,int a,int b) {
        for (int i = a; i < a+8; ++i) {
            for (int j = b; j < b+8; ++j) {
                actual[1][i][j] = 0;
                actual[2][i][j] = 0;
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

    private int[][][] quantize(float[][][] matrix, int[][][] actual, int a, int b, int ratioCompression) {
        double percentage = ratioCompression/100.0;
        for(int i = a; i < a+8; ++i) {
            for (int j = b; j < b+8; ++j) {
                double coeficient = QcTable[i%8][j%8]*percentage;
                coeficient = Math.max(coeficient, 1);
                actual[1][i][j] = (int) Math.round(matrix[1][i][j]/coeficient);
                actual[2][i][j] = (int) Math.round(matrix[2][i][j]/coeficient);
            }
        }
        return actual;
    }

    private float[][][] dequantize(int[][][] matrix, int ratioCompression) {
        double percentage = ratioCompression/100.0;
        float[][][] actual = new float[3][vsize][hsize];
        for(int i = 0; i < vsize; ++i)  {
            for (int j = 0; j < hsize; ++j) {
                actual[0][i][j] = matrix[0][i][j];
            }
        }
        for(int i = 0; i < ssVSize; ++i) {
            for (int j = 0; j < ssHSize; ++j) {
                double coeficient = QcTable[i%8][j%8]*percentage;
                coeficient = Math.max(coeficient, 1);
                actual[1][i][j] = (float) (matrix[1][i][j]*coeficient);
                actual[2][i][j] = (float) (matrix[2][i][j]*coeficient);
            }
        }
        return actual;
    }

    private ArrayList<Pair<Byte,Short>> runLengthEncode(int[][][] mat, int row, int col, ArrayList<Pair<Byte,Short>> result) {
        
        ZigZag rlCb = new ZigZag(mat[1], row, col);
        ZigZag rlCr = new ZigZag(mat[2], row, col);

        //Cb
        Byte cont = 0;
        while (!rlCb.isEnd()) {
            Integer num = rlCb.getNextNumber();
            if (!num.equals(0)) {
                Short value = new Short(num.shortValue());
                Pair<Byte,Short> newPair = new Pair<Byte,Short>(cont,value);
                result.add(newPair);
                cont = 0;
            }
            else {
                ++cont;
            }
        }
        cont = 0;
        Pair<Byte,Short> temp = new Pair<Byte,Short>(new Byte((byte)0),new Short((short)0));
        result.add(temp);
        //Cr
        while (!rlCr.isEnd()) {
            Integer num = rlCr.getNextNumber();
            if (!num.equals(0)) {
                Short value = new Short(num.shortValue());
                Pair<Byte,Short> newPair = new Pair<Byte,Short>(cont,value);
                result.add(newPair);
                cont = 0;
            }
            else {
                ++cont;
            }
        }
        Pair<Byte,Short> temp2 = new Pair<Byte,Short>(new Byte((byte)0),new Short((short)0));
        result.add(temp2);
        return result;
    }

    private int[][][] runLengthDecode(ArrayList<Pair<Byte,Short>> encoded, int[][][] decoded,int[][] luminance) {
        int matNum = 0;
        int index = 0;
        ZigZag[] rl = new ZigZag[2];
        decoded = new int[3][ssVSize][ssHSize];
        rl[0] = new ZigZag(ssVSize, ssHSize,0,0);
        rl[1] = new ZigZag(ssVSize, ssHSize,0,0);
        Byte zerob = 0;
        Short zeros = 0;
        while (index < encoded.size()) {
            Pair<Byte,Short> p = encoded.get(index);
            ++index;
            if (p.getFirst().equals(zerob) && p.getSecond().equals(zeros)) {
                while (!rl[matNum].isEnd()) {
                    rl[matNum].writeNum(0);
                }
                rl[matNum].nextSquare();
                if(matNum == 1) matNum = 0;
                else matNum = 1;
            }
            else {
                int temp = p.getFirst();
                while (temp > zerob) {
                    --temp;
                    rl[matNum].writeNum(0);
                }
                rl[matNum].writeNum(p.getSecond());
            }
        }
        decoded[0] = luminance.clone();
        decoded[1] = rl[0].getMatrix().clone();
        decoded[2] = rl[1].getMatrix().clone();
        return decoded.clone();
    }

    public ImatgeComprimida comprimir(Imatge imatgeDescomprimida, int ratioCompression, String subsamplingString) throws IOException, VersionPPMIncorrecta, ExtensionIncorrecta, DatosIncorrectos {
        
        String[] subsamplingParsed = subsamplingString.split(":");
        int[] subsampling = new int[3];
        for (int i = 0; i < 3; ++i) {
            subsampling[i] = Integer.parseInt(subsamplingParsed[i]);
        }
        
        
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
        
        if (subsampling[2] == 0) {
            ssVSize = vsize / 2;
        }
        else {
            if (subsampling[2] != subsampling[1]) throw new DatosIncorrectos();
            ssVSize = vsize;
        }
        if (subsampling[1] == 0) throw new DatosIncorrectos();
        double div1 = subsampling[0]/subsampling[1];
        if ( ((int)(div1*100000))%100000 != 0) throw new DatosIncorrectos();
        ssHSize =  (int) (hsize / (div1));
         if (ssHSize%8 != 0) {
            ssHSize = ssHSize + (8 - (ssHSize % 8));
        }
        if (ssVSize%8 != 0) {
            ssVSize = ssVSize + (8 - (ssVSize % 8));
        }
        
        float[][][] YCbCr = RGBToYCbCr(imatgeDescomprimida.getContingut());
        
        System.out.println(ssHSize + " -- " + ssVSize);
        System.out.println(hsize + " -- " + vsize);
        
        float[][][] subsampled = subsample(YCbCr,subsampling);
        
        YCbCr = subtract128(subsampled);
        float[][][] DCTed = new float[3][ssVSize][ssHSize];
        int[][][] quantized = new int[3][ssVSize][ssHSize];
        ArrayList<Pair<Byte,Short>> encoded = new ArrayList<Pair<Byte,Short>>();
        for (int i = 0; i < ssVSize; i += 8) {
            for (int j = 0; j < ssHSize; j += 8) {
                DCTed     = doDCT(YCbCr,DCTed,i,j);
                quantized = quantize(DCTed,quantized,i,j,ratioCompression);
                encoded  = runLengthEncode(quantized,i,j, encoded);
            }
        }

        Huffman huffmanEncoder = new Huffman();
        byte[] huffmanEncoded = huffmanEncoder.huffmanEncode(encoded);

        byte[] resultContent = new byte[hsize*vsize + huffmanEncoded.length];

        for (int i = 0; i < vsize; ++i) {
            for (int j = 0; j < hsize; ++j) {
                resultContent[i*hsize + j] = (byte)YCbCr[0][i][j];
            }
        }
        for (int i = 0; i < huffmanEncoded.length; ++i) {
            resultContent[hsize*vsize + i] = huffmanEncoded[i];
        }
        long end = System.currentTimeMillis();
        String newPath = imatgeDescomprimida.getPath();
        newPath = newPath.replace(".ppm",".jimg");
        ImatgeComprimida imatgeComprimida = new ImatgeComprimida(newPath,resultContent, imatgeDescomprimida.getVersion(),origVsize, origHsize, imatgeDescomprimida.getMaxVal(), vsize, hsize, huffmanEncoder.getDecodingHashMap(),encoded.size(),ratioCompression,subsamplingString);
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
        String subsamplingString = imatgeComprimida.getSubsampling();
        int[] subsampling = new int[3];
        String[] subsamplingParsed = subsamplingString.split(":");
        for (int i = 0; i < 3; ++i) {
            subsampling[i] = Integer.parseInt(subsamplingParsed[i]);
        }
        ssVSize = vsize;
        if (subsampling[2] == 0) {
            ssVSize = vsize / 2;
        }
        else {
            if (subsampling[2] != subsampling[1]) throw new DatosIncorrectos();
        }
        if (subsampling[1] == 0) throw new DatosIncorrectos();
        double div1 = subsampling[0]/subsampling[1];
        if ( ((int)(div1*100000))%100000 != 0) throw new DatosIncorrectos();
        ssHSize =  (int) (hsize / (div1));
         if (ssHSize%8 != 0) {
            ssHSize = ssHSize + (8 - (ssHSize % 8));
        }
         
        if (ssVSize%8 != 0) {
            ssVSize = ssVSize + (8 - (ssVSize % 8));
        }
        
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
            huffmanDecoded = huffmanDecoder.huffmanDecode(content, imatgeComprimida.getNumPairs(),huffmanDecoded);
        }
        catch (Exception e) {
            throw new DatosIncorrectos();
        }

        int[][][] decoded = new int[3][ssVSize][ssHSize];
        decoded = runLengthDecode(huffmanDecoded,decoded,luminance);
        
        float[][][] unDCTed = new float[3][ssVSize][ssHSize];
        float[][][] unQuantized = new float[3][ssVSize][ssHSize];
        unQuantized = dequantize(decoded,imatgeComprimida.getRatioCompressio());
        for (int i = 0; i < ssVSize; i += 8) {
            for (int j = 0; j < ssHSize; j += 8) {
                unDCTed = undoDCT(unQuantized,unDCTed,i,j);
            }
        }
        unDCTed[0] = unQuantized[0].clone();
        unDCTed = add128(unDCTed);
        
        float [][][]unSubsampled = unSubsample(unDCTed, subsampling);

        byte [][][] RGB = YCbCrToRGB(unSubsampled);

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
