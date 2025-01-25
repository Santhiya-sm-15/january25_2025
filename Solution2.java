class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        Queue<int[]> q=new LinkedList<>();
        q.add(new int[]{sr,sc});
        int ini=image[sr][sc];
        int n=image.length,m=image[0].length;
        int[][] dir={{0,-1},{-1,0},{0,1},{1,0}};
        boolean[][] visited=new boolean[n][m];
        while(!q.isEmpty())
        {
            int[] x=q.poll();
            int r=x[0];
            int c=x[1];
            visited[r][c]=true;
            image[r][c]=newColor;
            for(int[] d:dir)
            {
                int nr=r+d[0];
                int nc=c+d[1];
                if(nr>=0 && nr<n && nc>=0 && nc<m && !visited[nr][nc] && image[nr][nc]==ini)
                    q.add(new int[]{nr,nc});
            }
        }
        return image;
    }
}