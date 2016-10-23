/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.io.*;

/**
 *
 * @author eviofragoso
 */
public class GraphLib {

    private int lines;
    private int arr[];
    private int vertex;

    public int getLines() {
        return lines;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }

    public int[] getArr() {
        return arr;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    public int getVertex() {
        return vertex;
    }

    public void setVertex(int vertex) {
        this.vertex = vertex;
    }

    public void getGraph(String fileName) throws Exception {

        FileReader fr = new FileReader(fileName);
        BufferedReader in = new BufferedReader(fr);

        int j = 0, linesc = 0, cont;
        int i;
        i = Integer.parseInt(in.readLine());
        in.mark(i);
        //System.out.println(i);
        int arrc[] = new int[i];
        String s;
        //System.out.println(arr.length);
        in.reset();
        while ((s = in.readLine()) != null && j< arrc.length) {
            System.out.println(s);
           // if (j > 0) {
                System.out.println((Character.getNumericValue(s.charAt(0)))-1);
                System.out.println((Character.getNumericValue(s.charAt(2)))-1);
                
                
                arrc[Character.getNumericValue(s.charAt(0))-1]++;
                arrc[Character.getNumericValue(s.charAt(2))-1]++;
                //System.out.println(arr[i - 1]);
           // }
            linesc++;
            j++;
        }
        //linesc = (int) in.lines().count();
        //linesc = linesc - 1;
        in.close();

        this.setArr(arrc);
        this.setLines(linesc);
        this.setVertex(arrc.length);

    }

    public void generalOutput(String fileName) {
        try {
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter out = new BufferedWriter(fw);
            out.write("# n = " + this.getVertex());
            out.newLine();
            out.write("# m = " + this.getLines());
            out.newLine();
            for (int i = 0; i < this.getArr().length; i++) {
                out.write((i + 1) + " " + this.getArr()[i]);
                out.newLine();
            }

            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("success");
    }

    public static void main(String[] args) throws Exception {

        GraphLib gl = new GraphLib();
        gl.getGraph("graph.txt");
        gl.generalOutput("graphOutput.txt");

    }

}
