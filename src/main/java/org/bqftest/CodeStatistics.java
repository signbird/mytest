package org.bqftest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 代码行数统计
 *
 */
public class CodeStatistics {

    int countSum;// 普通源代码行数
    int countCommnet;// 普通源代码注释行数
    int countSumTest;// 单元测试代码行数
    int countCommnetTest;// 单元测试代码注释行数

    static int javaClassTotal = 0;
    static int javaTestTotal = 0;

    public static void main(String[] args) throws Exception {

        RuntimeException run=new RuntimeException();
        
        
        // 源代码包顶级路径
        String filePath = "E:\\migu\\00work\\git\\封装层总";
        CodeStatistics fn = new CodeStatistics();
        File[] files = fn.getDirs(filePath);
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println(file.getName());
                fn.process(file);
                fn.clear();
                System.out.println("");
            }
        }
        /*
         * int[] countSumArray = fn.codeTotal(filePath1);
         * System.out.println("普通代码总行数 ->countSum: " + countSumArray[0]);
         * System.out.println("普通代码注释总行数 ->countCommnet: " + countSumArray[1]);
         * System.out.println("单元测试代码总行数 ->countSumTest: " + countSumArray[2]);
         * System.out.println("单元测试注释总行数 ->countCommnetTest: " +
         * countSumArray[3]); System.out.println("普通java类 ->countCommnetTest: "
         * + javaClassTotal); System.out.println("单元测试类 ->countCommnetTest: " +
         * javaTestTotal);
         */
    }

    public File[] getDirs(String filePath) {
        File file = new File(filePath);
        if (file.isDirectory()) {
            return file.listFiles();
        }
        throw new RuntimeException("请给一个文件夹路径");
    }

    public void process(File file) throws FileNotFoundException {
        int[] countSumArray = codeTotal(file);
        System.out.println("普通代码总行数 : " + countSumArray[0]);
        System.out.println("普通代码注释总行数 : " + countSumArray[1]);
        System.out.println("单元测试代码总行数 : " + countSumArray[2]);
        System.out.println("单元测试注释总行数 : " + countSumArray[3]);
        System.out.println("普通java类总数 : " + javaClassTotal);
        System.out.println("单元测试类总数 : " + javaTestTotal);
    }

    public void clear() {
        countSum = 0;
        countCommnet = 0;
        countSumTest = 0;
        countCommnetTest = 0;
        javaClassTotal = 0;
        javaTestTotal = 0;
    }

    public int[] codeTotal(File file) throws FileNotFoundException {
        try {
            if (file.isDirectory()) {// 如果为文件夹则递归
                File[] codeFiles = file.listFiles();
                int s = codeFiles.length;
                for (int i = 0; i < s; i++) {
                    codeTotal(codeFiles[i]);
                }
            } else {
                if (file.getName().endsWith(".java")) {// 判断是否是java文件
                    // 排除工具类代码 需要根据自己业务特性指定
                    if (!file.getName().contains("Criteria")) {// 排除工具自动生成的代码
                        if (file.getName().contains("Test")) {// 单元测试代码统计
                            getJavaTestLineCount(file);
                        } else {// 普通源代码统计
                            getJavaLineCount(file);
                        }
                    }
                }
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        return new int[] { countSum, countCommnet, countSumTest,
                countCommnetTest };
    }

    /**
     * 统计每个java文件 源代码行数、注释行数
     * 
     */
    private int[] getJavaLineCount(File file) {
        javaClassTotal++;
        int[] totalOneFile = fileListWithcomment(file);
        // System.out.println(file.getName() + ",   " + totalOneFile[0] + ","+
        // totalOneFile[1]);// 输出java文件名称
        countSum += totalOneFile[0];
        countCommnet += totalOneFile[1];
        return new int[] { countSum, countCommnet };
    }

    /**
     * 统计普通源代码及其注释行数
     * 
     */
    private int[] getJavaTestLineCount(File file) {
        javaTestTotal++;
        int[] totalOneFile = fileListWithcomment(file);
        // System.out.println(file.getName() + ",   " + totalOneFile[0] + ":"+
        // totalOneFile[1]);// 输出java文件名称
        countSumTest += totalOneFile[0];
        countCommnetTest += totalOneFile[1];
        return new int[] { countSumTest, countCommnetTest };
    }

    /**
     * 识别该文件中每行类型：普通代码行、空白行、注释行并做统计
     * 
     * 遗留问题：不合规范的代码注释行，可能统计不到
     * 
     */
    public int[] fileListWithcomment(File file) {
        int countSum = 0;// 代码行数
        int blankSum = 0;// 空白行数
        int commentSum = 0;// 注释行数
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.matches("^[\\s&&[^\\n]]*$")) {
                    // 不计入统计
                    blankSum++;
                } else if (line.startsWith("//")) {
                    // 识别为注释
                    commentSum++;
                } else if (line.startsWith("/*")) {
                    // 识别为注释
                    commentSum++;
                } else if (line.startsWith("*")) {
                    // 识别为注释
                    commentSum++;
                } else if (line.endsWith("*/")) {
                    // 识别为注释
                    commentSum++;
                } else {
                    countSum++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new int[] { countSum, commentSum };
    }
}