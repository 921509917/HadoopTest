package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
        public static void get(List<List<String>> list,int[] num,int n,int a){
            if(a==n){
                List<String> stringList = new ArrayList<String>(n);
                StringBuffer s= new StringBuffer();
                for(int i=0;i<n;i++){
                    s.setLength(0);
                    for(int j=0;j<n;j++){
                        s.append(".");
                    }
                    s.replace(num[i],num[i]+1,"Q");
                    stringList.add(s.toString());
                }
                list.add(stringList);
                return;
            }
            int[] newNum = new int[n];
            for(int i=0;i<a;i++){
                newNum[num[i]]=1;
                if((num[i]-(a-i))>=0){
                    newNum[num[i]-(a-i)]=1;
                }
                if((num[i]+(a-i))<n){
                    newNum[num[i]+(a-i)]=1;
                }
            }
            for(int i=0;i<newNum.length;i++){
                if(newNum[i]==0){
                    num[a]=i;
                    get(list,num,n,a+1);
                }
            }
        }
        public static List<List<String>> solveNQueens(int n) {
            int[] num = new int[n];
            for(int i=0;i<num.length;i++){
                num[i]=-1;
            }
            List<List<String>> list = new ArrayList<List<String>>();
            get(list,num,n,0);
            return list;
        }
        public static void main(String[] args) {
            System.out.println("请输入参数：");
            Scanner scanner = new Scanner(System.in);
            int i = Integer.parseInt(scanner.next());
            List<List<String>> lists = solveNQueens(i);
            for(List<String> one :lists){
                for(String two :one){
                    System.out.println(two);
                }
                System.out.println("");
            }
        }
    }
