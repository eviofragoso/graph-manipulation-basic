/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 2
 *
 * @author eviofragoso
 */
public class GraphLib {

    private int nlines;
    private int[] arrGraus;
    private int vertex;
    private ArrayList<String> edge;
    private ArrayList<ArrayList<Character>> adjcM;
    private ArrayList<ArrayList<Integer>> al;

    public int getNLines() {
        return this.nlines;
    }

    public void setNLines(int nlines) {
        this.nlines = nlines;
    }

    public int[] getArrGraus() {
        return arrGraus;
    }

    public void setArrGraus(int[] arrGraus) {
        this.arrGraus = arrGraus;
    }

    public int getVertex() {
        return vertex;
    }

    public void setVertex(int vertex) {
        this.vertex = vertex;
    }

    public ArrayList<String> getEdge() {
        return edge;
    }

    public void setEdge(ArrayList<String> edge) {
        this.edge = edge;
    }

    public ArrayList<ArrayList<Character>> getAdjcM() {
        return adjcM;
    }

    public void setAdjcM(ArrayList<ArrayList<Character>> adjcM) {
        this.adjcM = adjcM;
    }

    public ArrayList<ArrayList<Integer>> getAl() {
        return al;
    }

    public void setAl(ArrayList<ArrayList<Integer>> al) {
        this.al = al;
    }

    public void getGraph(String fileName) throws Exception {

        FileReader fr = new FileReader(fileName);
        BufferedReader in = new BufferedReader(fr);

        int j = 0, linesc = 0, cont;
        int i;
        i = Integer.parseInt(in.readLine());
        in.mark(i);
        //System.out.println(i);
        int[] arrc = new int[i];
        String s;
        //System.out.println(arr.length);
        in.reset();
        ArrayList<String> al = new ArrayList<String>();
        while ((s = in.readLine()) != null) {
            // System.out.println(s);
            // if (j > 0) {
//                System.out.println((Character.getNumericValue(s.charAt(0)))-1);
//                System.out.println((Character.getNumericValue(s.charAt(2)))-1);
//                
            al.add(s);
            String[] parts = s.split(" ");
            arrc[Integer.parseInt(parts[0]) - 1]++;
            arrc[Integer.parseInt(parts[1]) - 1]++;
            // arrc[Character.getNumericValue(s.charAt(0)) - 1]++;
            //arrc[Character.getNumericValue(s.charAt(2)) - 1]++;
            //System.out.println(arr[i - 1]);
            // }
            linesc++;
            j++;
        }
        //linesc = (int) in.lines().count();
        //linesc = linesc - 1;
        in.close();

        this.setArrGraus(arrc);
        this.setNLines(linesc);
        this.setVertex(arrc.length);
        this.setEdge(al);

    }

    public void generalOutput(String fileName) {
        try {
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter out = new BufferedWriter(fw);
            out.write("# n = " + this.getVertex());
            out.newLine();
            out.write("# m = " + this.getNLines());
            out.newLine();
            for (int i = 0; i < this.getArrGraus().length; i++) {
                out.write((i + 1) + " " + this.getArrGraus()[i]);
                out.newLine();
            }

            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("success");
    }

    public void gAdjacencyMatrix(String adjcMatrixFileName) {
        ArrayList<String> al = new ArrayList<>();
        //int[][] am = new int[this.getVertex()][this.getVertex()];
        ArrayList<ArrayList<Character>> am = new ArrayList<ArrayList<Character>>();
        for (int m = 0; m < this.getVertex(); m++) {
            am.add(new ArrayList<Character>());
            for (int n = 0; n < this.getVertex(); n++) {
                am.get(m).add('0');

            }
        }

        al = this.getEdge();
        //System.out.println(this.getVertex());
        for (String s : al) {
            String[] parts = s.split(" ");

            // am[Character.getNumericValue(s.charAt(0)) - 1][Character.getNumericValue(s.charAt(2)) - 1] = 1;
            am.get(Integer.parseInt(parts[0]) - 1).set(Integer.parseInt(parts[1]) - 1, '1');
            am.get(Integer.parseInt(parts[1]) - 1).set(Integer.parseInt(parts[0]) - 1, '1');
            // am[Character.getNumericValue(s.charAt(2)) - 1][Character.getNumericValue(s.charAt(0)) - 1] = 1;
            // System.out.println(am[Character.getNumericValue(s.charAt(0))][Character.getNumericValue(s.charAt(2))]);
        }

        try {
            FileWriter fw = new FileWriter(adjcMatrixFileName);
            BufferedWriter out = new BufferedWriter(fw);

            for (int i = 0; i < this.getVertex(); i++) {
                out.write("[");
                for (int j = 0; j < this.getVertex(); j++) {

                    out.write(" " + am.get(i).get(j) + " ");
                }
                out.write("]");
                out.newLine();
            }
            out.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }

        this.setAdjcM(am);

    }

    public void gAdjacencyList(String adjcListFileName) {
        ArrayList<ArrayList<Integer>> adjcl = new ArrayList<ArrayList<Integer>>();
        ArrayList<String> edges = new ArrayList<>();
        edges = this.getEdge();
        Integer aux;

        for (int m = 0; m < this.getVertex(); m++) {
            adjcl.add(new ArrayList<Integer>());
        }

        //System.out.println(this.getVertex());
        for (String s : edges) {

            String[] parts = s.split(" ");

            if (!adjcl.get(Integer.parseInt(parts[0]) - 1).contains(Integer.parseInt(parts[1]) - 1)) {
                adjcl.get(Integer.parseInt(parts[0]) - 1).add(Integer.parseInt(parts[1]) - 1);
            }
            if (!adjcl.get(Integer.parseInt(parts[1]) - 1).contains(Integer.parseInt(parts[0]) - 1)) {
                adjcl.get(Integer.parseInt(parts[1]) - 1).add(Integer.parseInt(parts[0]) - 1);
            }

        }

        try {
            FileWriter fw = new FileWriter(adjcListFileName);
            BufferedWriter out = new BufferedWriter(fw);

            for (int i = 0; i < this.getVertex(); i++) {
                out.write("(" + (i + 1) + ")" + "->");
                for (int j = 0; j < adjcl.get(i).size(); j++) {
                    aux = adjcl.get(i).get(j) + 1;
                    out.write(aux.toString());
                    if (j != adjcl.get(i).size() - 1) {
                        out.write("->");
                    }
                }
                out.newLine();
            }
            out.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }

        this.setAl(adjcl);
        //System.out.println(al.get(0));

    }

    //Busca em larguraGraphLib.java:245
    //ERROR TO BE FIXED WHERE CHILD NODES ARE LISTED AS THE SAME LVL AS THEIR PARENT
    public void breadthFirstSearchAL(int iniVertex, String outputName) {
        try {
            ArrayList<ArrayList<Integer>> list = this.getAl();
            int lvl = 1, parent, aux;
            FileWriter fw = new FileWriter(outputName);
            BufferedWriter out = new BufferedWriter(fw);
            Queue q = new LinkedList();

            // ArrayList<ArrayList<Integer>> list = this.getAl();
            ArrayList<Integer> visited = new ArrayList<>();
            //System.out.println(this.getAl());
            // System.out.println(list.get(iniVertex-1));
            // Node root = new Node();
            //root.setValue(list.get(iniVertex-1).get(0));
            //q.add(root);
            int pos = iniVertex - 1;
            q.add(pos);
            out.write("# First Node lvl is 0");
            out.newLine();
            out.write("# Order of writting: Vertex level Parent(If exists)");
            out.newLine();
            out.write(iniVertex + " " + '0');
            out.newLine();
            visited.add(pos);
            aux = lvl;
            while (!q.isEmpty()) {
                //Node n = (Node) q.remove();
                //ArrayList<Node> childN = null;
                parent = (Integer) q.remove();
                ArrayList<Integer> child = list.get(parent);
                aux = lvl;
                for (Integer i : child) {

                    if (!visited.contains(i)) {
                        //Node n1 = new Node();
                        // n1.setParent(n);
                        //n1.setValue(i);
                        //childN.add(n1);
                        out.write(i + 1 + " " + lvl + " " + (parent + 1));
                        out.newLine();
                        visited.add(i);
                        q.add(i);
                        aux++;

                    }

                }
                if (aux != lvl) {
                    lvl++;
                }
            }
            out.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    public void breadthFirstSearchAM(int iniVertex, String outputName) {
        try {
            ArrayList<ArrayList<Character>> list = this.getAdjcM();
            int lvl = 1, parent, aux;
            FileWriter fw = new FileWriter(outputName);
            BufferedWriter out = new BufferedWriter(fw);
            Queue q = new LinkedList();

            // ArrayList<ArrayList<Integer>> list = this.getAl();
            ArrayList<Integer> visited = new ArrayList<>();
            //System.out.println(this.getAl());
            // System.out.println(list.get(iniVertex-1));
            // Node root = new Node();
            //root.setValue(list.get(iniVertex-1).get(0));
            //q.add(root);
            int pos = iniVertex - 1;
            q.add(pos);
            out.write("# First Node lvl is 0");
            out.newLine();
            out.write("# Order of writting: Vertex level Parent(If exists)");
            out.newLine();
            out.write(iniVertex + " " + '0');
            out.newLine();
            visited.add(pos);
            aux = lvl;
            ///int j=0;
            while (!q.isEmpty()) {
                //Node n = (Node) q.remove();
                //ArrayList<Node> childN = null;

                parent = (Integer) q.remove();
                ArrayList<Character> child = list.get(parent);
                aux = lvl;
                //System.out.println(child.size());
                for (int i = 0; i < child.size(); i++) {

                    if (!visited.contains(i) && child.get(i) == '1') {
                        //Node n1 = new Node();
                        // n1.setParent(n);
                        //n1.setValue(i);
                        //childN.add(n1);
                        out.write(i + 1 + " " + lvl + " " + (parent + 1));
                        out.newLine();
                        visited.add(i);
                        q.add(i);
                        aux++;

                    }

                }
                if (aux != lvl) {
                    lvl++;
                }
                // j++;
            }
            out.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    public void depththFirstSearchAL(int iniVertex, String outputName) {
        Stack s = new Stack();
        int aux, lvl = 1;
        int pos = iniVertex - 1;
        s.push(pos);
        ArrayList<Integer> visited = new ArrayList<>();
        ArrayList<ArrayList<Integer>> list = this.getAl();
        try {
            FileWriter fw = new FileWriter(outputName);
            BufferedWriter out = new BufferedWriter(fw);
            out.write("# First Node lvl is 0");
            out.newLine();
            out.write("# Order of writting: Vertex level Parent(If exists)");
            out.newLine();
            out.write(iniVertex + " " + '0');
            out.newLine();
            visited.add(pos);
            

            while (!s.isEmpty()) {
                Integer parent = (Integer) s.peek();

                ArrayList<Integer> allchilds = list.get(parent);
                ArrayList<Integer> childs = new ArrayList<>();
                // aux = lvl;
                System.out.println(allchilds.size());
                for (Integer allc : allchilds) {
                    if (!visited.contains(allc)) {
                        childs.add(allc);
                    }

                }

                if (childs.size()>0) {
                    
                    out.write(childs.get(0) + 1 + " " + lvl + " " + (parent + 1));
                    out.newLine();
                    visited.add(childs.get(0));
                    s.push(childs.get(0));
                    
                    lvl++;
                } else {

                    s.pop();
                    lvl--;
                }
                

            }
            out.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
     public void depththFirstSearchAM(int iniVertex, String outputName){
      Stack s = new Stack();
        int aux, lvl = 1;
        int pos = iniVertex - 1;
        s.push(pos);
        ArrayList<Integer> visited = new ArrayList<>();
        ArrayList<ArrayList<Character>> list = this.getAdjcM();
        try {
            FileWriter fw = new FileWriter(outputName);
            BufferedWriter out = new BufferedWriter(fw);
            out.write("# First Node lvl is 0");
            out.newLine();
            out.write("# Order of writting: Vertex level Parent(If exists)");
            out.newLine();
            out.write(iniVertex + " " + '0');
            out.newLine();
            visited.add(pos);
            

            while (!s.isEmpty()) {
                Integer parent = (Integer) s.peek();

                ArrayList<Character> allchilds = list.get(parent);
                ArrayList<Integer> childs = new ArrayList<>();
                // aux = lvl;
                System.out.println(allchilds.size());
                for (int i =0;i<allchilds.size();i++) {
                    if (!visited.contains(i) && allchilds.get(i) == '1') {
                        childs.add(i);
                    }

                }

                if (childs.size()>0) {
                    
                    out.write(childs.get(0) + 1 + " " + lvl + " " + (parent + 1));
                    out.newLine();
                    visited.add(childs.get(0));
                    s.push(childs.get(0));
                    
                    lvl++;
                } else {

                    s.pop();
                    lvl--;
                }
                

            }
            out.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
     
     
     
     }

    public static void main(String[] args) throws Exception {

        GraphLib gl = new GraphLib();
        gl.getGraph("graph.txt");
        // gl.generalOutput("graphOutput.txt");
        gl.gAdjacencyMatrix("adjcMatrix.txt");
        //gl.gAdjacencyList("adjcList.txt");
        //gl.breadthFirstSearchAL(6, "BFSAL.txt");
        //gl.breadthFirstSearchAM(6, "BFSAM.txt");
      //  gl.depththFirstSearchAL(1, "DPSAL");
        gl.depththFirstSearchAM(1, "DPSAM");
    }

}
