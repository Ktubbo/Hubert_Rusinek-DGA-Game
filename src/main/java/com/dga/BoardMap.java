package com.dga;

import java.util.*;

public class BoardMap {

    Map<Integer,int[]> boardMap = new HashMap<>();
    List<int[]> cornerList = new ArrayList<>();


    public BoardMap(){

        cornerList.add(new int[]{4, 0});
        cornerList.add(new int[]{6, 0});
        cornerList.add(new int[]{6, 4});
        cornerList.add(new int[]{10, 4});
        cornerList.add(new int[]{10, 6});
        cornerList.add(new int[]{6, 6});
        cornerList.add(new int[]{6, 10});
        cornerList.add(new int[]{4, 10});
        cornerList.add(new int[]{4, 6});
        cornerList.add(new int[]{0, 6});
        cornerList.add(new int[]{0, 4});
        cornerList.add(new int[]{4, 4});
        cornerList.add(new int[]{4, 0});
        int k=2;

        boardMap.put(1,new int[]{4,0});
        for(int i=0;i<cornerList.size()-1;i++){
            for(int j=0;j<insertBoardLine(cornerList.get(i), cornerList.get(i+1)).size();j++){
                boardMap.put(k,insertBoardLine(cornerList.get(i), cornerList.get(i+1)).get(j));
                k++;
            }
        }

        boardMap.remove(41);

        addWinPositions(43,new int[]{5,1},new int[]{5,4});
        addWinPositions(53,new int[]{9,5},new int[]{6,5});
        addWinPositions(63,new int[]{5,9},new int[]{5,6});
        addWinPositions(73,new int[]{1,5},new int[]{4,5});
    }

    public List<int[]> insertBoardLine(int[] a,int[] b){

        List<int[]> lineList = new ArrayList<>();
        if(a[0]==b[0]){
            for(int i=1;i<=Math.abs(a[1]-b[1]);i++){
                if(a[1]>b[1]){
                    lineList.add(new int[]{a[0],a[1]-i});
                } else {
                    lineList.add(new int[]{a[0],a[1]+i});
                }
            }
        }

        if(a[1]==b[1]){
            for(int i=1;i<=Math.abs(a[0]-b[0]);i++){
                if(a[0]>b[0]){
                    lineList.add(new int[]{a[0]-i,a[1]});
                } else {
                    lineList.add(new int[]{a[0]+i,a[1]});
                }
            }
        }

        return lineList;
    }


    public Map<Integer,int[]> getBoardMap(){
        return boardMap;
    }

        public Optional<Integer> getKey(int[] position){

        return boardMap
                .entrySet()
                .stream()
                .filter(e -> Objects.equals(e.getValue()[0],position[0]))
                .filter(e -> Objects.equals(e.getValue()[1],position[1]))
                .map(Map.Entry::getKey)
                .findAny();
    }

    public void addWinPositions(int startValue, int[] a, int[] b){
        boardMap.put(startValue,a);
        for(int i=0;i<insertBoardLine(a,b).size();i++){
            boardMap.put(startValue+1+i,insertBoardLine(a,b).get(i));
        }
    }
}
