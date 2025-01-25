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