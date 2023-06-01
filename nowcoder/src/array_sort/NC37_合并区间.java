package array_sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 给出一组区间，请合并所有重叠的区间。
 * 请保证合并后的区间按区间起点升序排列。
 *
 * 解题思路
 * 什么样的区间能够合并，那肯定是有交叉的区间，即后一个区间的尾小于前一个区间的首，这时候可以将这种交叉区间的尾合并延长区间：
 * //区间有重叠，更新结尾
 * if(intervals[i].start <= res.back().end)
 *     res.back().end = max(res.back().end, intervals[i].end);
 * 那我们肯定是区间在一定程度上有序的才可以方便比较（区间有两个边界值，完全有序不可能，但是可以按照区间首排序），
 * 这时候只要遍历到交叉的情况，就利用贪心思想，一直合并，直到不能合并为止。
 *
 * 具体做法：
 *
 * step 1：既然要求重叠后的区间按照起点位置升序排列，我们就将所有区间按照起点位置先进行排序。使用sort函数进行排序，重载比较方式为比较interval结构的start变量。
 * step 2：排序后的第一个区间一定是起点值最小的区间，我们将其计入返回数组res，然后遍历后续区间。
 * step 3：后续遍历过程中，如果遇到起点值小于res中最后一个区间的末尾值的情况，那一定是重叠，取二者最大末尾值更新res中最后一个区间即可。
 * step 4：如果遇到起点值大于res中最后一个区间的末尾值的情况，那一定没有重叠，后续也不会有这个末尾的重叠区间了，因为后面的起点只会更大，因此可以将它加入res。
 */
public class NC37_合并区间 {
    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        ArrayList<Interval> res = new ArrayList<>();
        //去除特殊情况
        if(intervals.size() == 0)
            return res;
        //重载比较，按照区间首排序
        Collections.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval o1, Interval o2){
                if(o1.start != o2.start)
                    return o1.start - o2.start;
                else
                    return o1.end - o2.end;
            }
        });
        //放入第一个区间
        res.add(intervals.get(0));
        int count = 0;
        //遍历后续区间，查看是否与末尾有重叠
        for(int i = 1; i < intervals.size(); i++){
            Interval o1 = intervals.get(i);
            Interval origin = res.get(count);
            if(o1.start > origin.end){
                res.add(o1);
                count++;
                //区间有重叠，更新结尾
            }else{
                res.remove(count);
                Interval s = new Interval(origin.start, o1.end);
                if(o1.end < origin.end)
                    s.end = origin.end;
                res.add(s);
            }
        }
        return res;
    }
}

 class Interval {
     int start;
     int end;
     Interval() { start = 0; end = 0; }
     Interval(int s, int e) { start = s; end = e; }
 }
