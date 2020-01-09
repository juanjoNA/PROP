package CapaDomini.ModelDomini;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.lang.StringBuilder;
import java.util.Map;
import CapaDomini.ModelDomini.Pair;

public class Huffman {
    
    class HuffmanNode {
    Pair<Byte,Short> content;
    int freq;
    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(Pair<Byte,Short> p, int f, HuffmanNode l, HuffmanNode r) {
        content = p;
        freq = f;
        left = l;
        right = r;
    }
  }

class HuffmanComparator implements Comparator<HuffmanNode> {
    @Override
    public int compare (HuffmanNode a, HuffmanNode b) {
        return a.freq - b.freq;
    }
}

class CodeNode {
    Pair<Byte,Short> data;
    String code;

    public CodeNode(Pair<Byte,Short> d, String c) {
        data = d;
        code = c;
    }
}


    String codesString;
    HashMap <String,Pair<Byte,Short>> decodificationTable;
    ArrayList <CodeNode> codes;

     /**
     * Constructora por defecto
     */
    public Huffman() {

    }

    /**
     * Constructora con un decodeHashMap
     * @param decodeHashMap
     */
    public Huffman(HashMap <String,Integer> decodeHashMap) {
        decodificationTable = new HashMap<String, Pair<Byte,Short>>();
        for (Map.Entry<String,Integer> e: decodeHashMap.entrySet()) {
            byte first = e.getValue().byteValue();
            Integer temp =(e.getValue() >> 8) ;
            short second = temp.shortValue();
           decodificationTable.put(e.getKey(),new Pair<Byte,Short>(first,second));
        }
    }

     /**
     * Funcion para recorrerte un arbol de huffmanNode y sacar los codigos huffman del arbol
     * @param root
     * @param code
     */
    private ArrayList<CodeNode>buildCodes(HuffmanNode root,String code) {
        if (root.left == null && root.right == null) {
            ArrayList<CodeNode> newCodeArray = new ArrayList<CodeNode>();
            newCodeArray.add(new CodeNode(root.content,code));
            return newCodeArray;
        }
        else {
            ArrayList<CodeNode> leftCodes = buildCodes(root.left, code+"0");
            ArrayList<CodeNode> rightCodes = buildCodes(root.right, code+"1");
            leftCodes.addAll(rightCodes);
            return leftCodes;
        }
    }

    /**
     * Funcion que dado un arrayList de pairs (Byte,Short) genera un arbol huffman y calcula los nodos.
     * @param list
     */
    private void buildTree(ArrayList<Pair<Byte,Short>> list) {
        HashMap<Pair<Byte,Short>, Integer> frequencies = new HashMap<Pair<Byte,Short>, Integer>();
        for (Pair<Byte,Short> p : list) {
            frequencies.computeIfAbsent(p, k -> 0);
            frequencies.computeIfPresent(p, (k,v) -> v+1);
        }
        PriorityQueue<HuffmanNode> nodes = new PriorityQueue<HuffmanNode>(new HuffmanComparator());
        for (Map.Entry<Pair<Byte,Short>,Integer> entry : frequencies.entrySet()) {
            HuffmanNode newNode = new HuffmanNode(entry.getKey(),entry.getValue(),null,null);
            nodes.add(newNode);
        }
        while(nodes.size() > 1) {
            HuffmanNode least = nodes.peek();
            nodes.remove();
            HuffmanNode secondLeast = nodes.peek();
            nodes.remove();
            HuffmanNode newNode = new HuffmanNode(null,least.freq+secondLeast.freq, least,secondLeast);
            nodes.add(newNode);
        }
        HuffmanNode root = nodes.peek();
        codes = buildCodes(root, "");
    }

    /**
     * Funcion para construir el hashMap de decodificacion a partir del array de codigos
     */
    private void buildDecodeHashMap() {
        decodificationTable = new  HashMap <String,Pair<Byte,Short>>();
        for (CodeNode codeNode :  codes) {
            decodificationTable.put( codeNode.code,codeNode.data);
        }
    }

    /**
     * Funcion para codificar un ArrayList de pair(byte,short) usando codigos huffman
     * @param contentToEncode
     * @return contenido codificado(byte[])
     */
    public byte[] huffmanEncode(ArrayList<Pair<Byte,Short>> contentToEncode) {                
        buildTree(contentToEncode);
        ArrayList<Byte> encodedContent = new ArrayList<Byte>();

        HashMap <Pair<Byte,Short>, String> codifier =  new  HashMap <Pair<Byte,Short>,String>();

        for (CodeNode codeNode :  codes) {
            codifier.put(codeNode.data,codeNode.code);
        }
        StringBuilder s = new StringBuilder();
        int index = 0;
        byte payload = 0;
        for (Pair<Byte,Short> actual : contentToEncode) {
            String pairCode = codifier.get(actual);
            for (int j = 0; j < pairCode.length(); ++j) {
                if (pairCode.charAt(j) == '1') {
                    payload = (byte)(payload << 1);
                    payload += 1;
                }
                else {
                    payload = (byte)(payload << 1);
                }
                ++index;
                if (index == 8) {
                    encodedContent.add(payload);
                    payload = 0x00;
                    index = 0;
                }
            }
        }
        codesString = s.toString();
        if (index > 0) {
            payload = (byte)(payload << (8-index));
        }
        encodedContent.add(payload);
        buildDecodeHashMap();
        byte[] result = new byte[encodedContent.size()];
        for (int i = 0; i < encodedContent.size(); ++i) {
            result[i] = encodedContent.get(i);
        }
        return result;
    }

    /**
     * Funcion para dados un array de bytes con contenido codificado en huffman, el numero de datos y un arraylist vacio decodifica con el decodingHashMap los datos del byte[]
     * @param data
     * @param numPairs
     * @param decodedData
     * @return
     */
    public ArrayList<Pair<Byte,Short>>huffmanDecode(byte[] data,int numPairs, ArrayList<Pair<Byte,Short>> decodedData) {
        int flag = 0x0080;
        StringBuilder builder = new StringBuilder();
        
        for (byte actual : data) {
            while (flag != 0x00 && decodedData.size() < numPairs) {
                if ((actual&flag) > ((byte)0)) {
                    builder.append('1');
                }
                else {
                    builder.append('0');
                }
                if (decodificationTable.containsKey(builder.toString())) {
                    Pair<Byte,Short> a = decodificationTable.get(builder.toString());
                    decodedData.add(a);
                    builder.setLength(0);
                }
                flag = flag>>>1;
            }
            flag = 0x0080;
        }
        return decodedData;
    }
    
    /**
     * Funcion que devuelve el hashMap para decodificar el contenido
     * @return HashMap(String,Integer)
     */
    public HashMap <String, Integer> getDecodingHashMap() {
        HashMap<String ,Integer> decoding = new HashMap<String,Integer>();
        for(Map.Entry<String,Pair<Byte,Short>> e : decodificationTable.entrySet()) {
            Integer second = (e.getValue().getSecond() << 8) + e.getValue().getFirst();
            decoding.put(e.getKey(), second);
        }
         return decoding;
    }
    
    /**
     * Funcion para poner el hashMap de decodificacion
     * @param decodeHashMap
     */
    public void setDecodingHashMap(HashMap <String, Integer> decodeHashMap) {
        decodificationTable = new HashMap<String, Pair<Byte,Short>>();
        for (Map.Entry<String,Integer> e: decodeHashMap.entrySet()) {
            byte first = e.getValue().byteValue();
            Integer temp =(e.getValue() >> 8) ;
            short second = temp.shortValue();
           decodificationTable.put(e.getKey(),new Pair<Byte,Short>(first,second));
        }
    }
}
