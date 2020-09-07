package com.dataStructures.huffmancode;

import java.io.*;
import java.util.*;

/**
 * @Author: md
 * @Date: 2020/8/22 13:01
 */
public class HuffmanCode {
    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        byte[] contentBytes = str.getBytes();
        /*System.out.println(contentBytes.length);
        List<Node> nodes = getNodes(contentBytes);
        System.out.println(nodes);
        Node huffmanTree = createHuffmanTree(nodes);
        preOrder(huffmanTree);
        Map<Byte, String> codes = getCodes(huffmanTree);
        System.out.println("生成的Huffman编码表："+codes);
        byte[] zip = zip(contentBytes, huffmanCodes);
        System.out.println(Arrays.toString(zip));*/
      /*  byte[] bytes = huffmanZip(contentBytes);
        System.out.println(Arrays.toString(bytes));
        String s = byteToBitString(true,(byte) 10);
        System.out.println(s);
        byte[] decode = decode(huffmanCodes, bytes);
        System.out.println(new String(decode));

        String srcFile = "D:\\huffman\\school.jpg";
        String dstFile = "D:\\huffman\\school.zip";
        zipFile(srcFile,dstFile);
        String zipFile = "D:\\huffman\\school.zip";
        String dstZipFile = "D:\\huffman\\school1.jpg";
        unZipFile(zipFile,dstZipFile);*/
        System.out.println(byteToBitString(true, (byte) 0));
    }

    //编写方法，将一个文件进行压缩
    /**
     *
     * @param srcFile 压缩文件的路径
     * @param dstFile 压缩文件后存放的目录
     */
    private static void zipFile(String srcFile,String dstFile){
        //创建输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        //创建文件输入流
        FileInputStream is = null;
        try {
            is = new FileInputStream(srcFile);
            //创建和源文件大小一样的byte[]
            byte[] b = new byte[is.available()];
            is.read(b);
            //获取文件对应的赫夫曼编码表
            byte[] huffmanBytes = huffmanZip(b);
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //把压缩后的数组写进去
            oos.writeObject(huffmanBytes);

            //注意一定要将赫夫曼编码写入压缩文件
            oos.writeObject(huffmanCodes);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
                oos.close();
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //编写一个方法，完成对压缩文件的解压

    /**
     *
     * @param zipFile 准备解压的文件
     * @param dstFile 解压后的文件
     */
    private static void unZipFile(String zipFile,String dstFile){
        //定义文件的输入流
        InputStream is = null;
        //定义一个对象输入流
        ObjectInputStream ois = null;
        //定义文件输出流
        OutputStream os = null;

        try {
            is = new FileInputStream(zipFile);
            //创建和is关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取byte数组
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //读取赫夫曼编码表
            Map<Byte,String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            //解码
            byte[] decode = decode(huffmanCodes, huffmanBytes);
            //将decode写入目标文件
            os = new FileOutputStream(dstFile);
            //写出数据
            os.write(decode);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                os.close();
                ois.close();
                is.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    //完成数据的解压
    /**
     * 将一个byte转成二进制字符串
     * @param b
     * @param flag 标志是否需要补高位，如果是true，表示需要补高位，如果是false，表示不补
     * @return
     */
    private static String byteToBitString(boolean flag ,byte b){
        //使用一个变量保存b
        int temp = b;
        if (flag){
            temp |= 256;
        }
        String toBinaryString = Integer.toBinaryString(temp);

        if (flag){
            return toBinaryString.substring(toBinaryString.length() - 8);
        }else {
            return toBinaryString;
        }
    }

    //编写一个方法，完成对数据的解码
    /**
     *
     * @param huffmanCodes 赫夫曼编码表
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte,String> huffmanCodes,byte[] huffmanBytes){
        //先得到huffmanBytes对应的二进制字符串，形式10110101000
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            if (i == huffmanBytes.length - 1 & huffmanBytes[i] >= 0){
                stringBuilder.append(byteToBitString(false,huffmanBytes[i]));
            }else {
                stringBuilder.append(byteToBitString(true,huffmanBytes[i]));
            }
        }
        //把字符串按照指定的赫夫曼编码进行解码
        Map<String,Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> stringByteEntry : huffmanCodes.entrySet()) {
            map.put(stringByteEntry.getValue(),stringByteEntry.getKey());
        }

        //创建集合存放byte
        ArrayList<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length();) {
            int count = 1;
            boolean flag = true;
            Byte b= null;
            while (flag){
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                if (b == null){
                    count ++;
                }else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        byte[] b = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            b[i] = list.get(i);
        }
        return b;
    }



    //-------------------------------------------------------------------------------------------------------------------------------------


    //使用一个方法，将前面的方法封装起来，便于我们调用
    /**
     *编码
     * @param bytes String原始数组
     * @return
     */
    private static byte[] huffmanZip(byte[] bytes){
        List<Node> nodes = getNodes(bytes);
        //根据nodes创建赫夫曼树
        Node huffmanTree = createHuffmanTree(nodes);
        //对应的赫夫曼编码
        Map<Byte, String> codes = getCodes(huffmanTree);
        System.out.println(codes);
        //压缩后的编码
        byte[] zip = zip(bytes, codes);
        return zip;
    }

    //编写一个方法，将一个字符串对应的byte[]数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码压缩后的byte[]
    /**
     * 8位对应一个byte，放入huffmanCodeBytes
     * @param bytes
     * @param huffmanCodes
     * @return
     */
    private static byte[] zip(byte[] bytes,Map<Byte,String> huffmanCodes){
        //利用huffmanCodes将bytes转成赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        //将字符串转byte[]数组
        //统计返回的huffmanCodeBytes长度
        //一句话 int len = (stringBuilder.length() + 7) / 8;
        int len;
        if (stringBuilder.length() % 8 == 0){
            len = stringBuilder.length() / 8;
        }else {
            len = stringBuilder.length() / 8 + 1;
        }
        //创建存储压缩后的byte[]
        byte[] by = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String str;
            if ((i + 8) > stringBuilder.length()){
                str = stringBuilder.substring(i);
            }else {
                str = stringBuilder.substring(i,i+8);
            }
            //将str转成byte，放入by中
            by[index] = (byte) Integer.parseInt(str,2);
            index ++;
        }
        return by;
    }



    /**
     * 接收字节数组
     * @param bytes
     * @return 返回list
     */
    private static List<Node> getNodes(byte[] bytes){
        List<Node> nodes = new ArrayList<>();
        //存储每个byte出现的次数
        Map<Byte,Integer> counts = new HashMap();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null){
                counts.put(b,1);
            }else {
                counts.put(b,count+1);
            }
        }
        //把每个键值对转换成Node对象，假如nodes集合
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(),entry.getValue()));
        }
        return nodes;
    }

    //通过对应的list创建赫夫曼树
    private static Node createHuffmanTree(List<Node> nodes){
        while (nodes.size() > 1){
            Collections.sort(nodes);
            Node nodeLeft = nodes.get(0);
            Node nodeRight = nodes.get(1);
            Node nodeParent = new Node(null, nodeLeft.weight + nodeRight.weight);
            nodeParent.left = nodeLeft;
            nodeParent.right = nodeRight;
            nodes.remove(nodeLeft);
            nodes.remove(nodeRight);
            nodes.add(nodeParent);
        }
        return nodes.get(0);
    }

    /**
     * 生成赫夫曼树对应的赫夫曼编码
     * 将赫夫曼编码表存放在Map中
     * 在生成赫夫曼编码表时，需要拼接路径，定义一个StringBuilder 存储某个叶子节点的路径
     * @param node
     */
    static StringBuilder stringBuilder = new StringBuilder();
    static Map<Byte,String> huffmanCodes = new HashMap<>();

    /**
     * 将传入的node节点的所有叶子节点的赫夫曼编码得到，并放入到huffmanCodes集合
     * @param node  传入接点
     * @param code  路径：左子节点0；右子节点1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder){
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        //将code假如stringBulider1
        stringBuilder1.append(code);
        if (node != null){
            if (node.data == null){ //非叶子节点
                getCodes(node.left,"0",stringBuilder1);
                getCodes(node.right,"1",stringBuilder1);
            }else {
                //表示找到了某个叶子节点的最后
                huffmanCodes.put(node.data,stringBuilder1.toString());
            }
        }
    }
    //为了调用方便，重载
    private static Map<Byte,String> getCodes(Node node){
        if (node == null){
            return null;
        }
        getCodes(node.left,"0",stringBuilder);
        getCodes(node.right,"1",stringBuilder);
        return huffmanCodes;
    }



    private static void preOrder(Node node){
        if (node == null){
            System.out.println("空树");
        }else {
            node.preOrder();
        }
    }
}

class Node implements Comparable<Node>{
    Byte data;
    int weight;
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
}
