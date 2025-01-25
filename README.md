# january25_2025
The problems that I solved today

1.You are given a 0-indexed array of positive integers nums and a positive integer limit. In one operation, you can choose any two indices i and j and swap nums[i] and nums[j] if |nums[i] - nums[j]| <= limit. Return the lexicographically smallest array that can be obtained by performing the operation any number of times. An array a is lexicographically smaller than an array b if in the first position where a and b differ, array a has an element that is less than the corresponding element in b. For example, the array [2,10,3] is lexicographically smaller than the array [10,2,3] because they differ at index 0 and 2 < 10.

Code:
class Disjoint
{
    int[] parent;
    int[] rank;
    public Disjoint(int n)
    {
        rank=new int[n];
        parent=new int[n];
        for(int i=0;i<n;i++)
            parent[i]=i;
    }
    public int find(int x)
    {
        if(parent[x]==x)
            return x;
        return parent[x]=find(parent[x]);
    }
    public void union(int u,int v)
    {
        int pu=find(u);
        int pv=find(v);
        if(pu!=pv)
        {
            if(rank[pu]>rank[pv])
                parent[pv]=pu;
            else if(rank[pu]<rank[pv])
                parent[pu]=pv;
            else
            {
                parent[pv]=pu;
                rank[pu]++;
            }
        }
    }
}
class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n=nums.length,i,j;
        Disjoint d=new Disjoint(n);
        List<Integer> l=new ArrayList<>();
        for(i=0;i<n;i++)
            l.add(i);
        Collections.sort(l,(a,b)->Integer.compare(nums[a],nums[b]));
        for(i=0;i<n-1;i++)
        {
            if(Math.abs(nums[l.get(i)]-nums[l.get(i+1)])<=limit)
                d.union(l.get(i),l.get(i+1));
        }
        Map<Integer,List<Integer>> m=new HashMap<>();
        for(i=0;i<n;i++)
        {
            int x=d.find(i);
            if(!m.containsKey(x))
                m.put(x,new ArrayList<>());
            m.get(x).add(i);
        }
        int[] res=new int[n];
        for (List<Integer> group : m.values()) {
            List<Integer> values = new ArrayList<>();
            for (int index : group)
                values.add(nums[index]);
            Collections.sort(values);
            int idx = 0;
            for (int index : group)
                res[index] = values.get(idx++);
        }
        return res;
    }
}

2.An image is represented by a 2-D array of integers, each integer representing the pixel value of the image. Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image. To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on. Replace the color of all of the aforementioned pixels with the new color.

Code:
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
