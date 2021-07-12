### 解题思路
两百多斤的嘤国大理石,掰不动我这一个快排

![QQ截图20201116095819.jpg](https://pic.leetcode-cn.com/1605492066-ryykYv-QQ%E6%88%AA%E5%9B%BE20201116095819.jpg)

Log:
2020-11-16
在 Java 提交中首创快速排序最优解：

![QQ截图20201116161208.jpg](https://pic.leetcode-cn.com/1605514356-rvqEPD-QQ%E6%88%AA%E5%9B%BE20201116161208.jpg)

2021-02-27
练习时长五天半的C语言练习生补充了C解fan法yi

### 代码

```java []
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        quickSort(people, 0, people.length - 1);
        List<int[]> list = new ArrayList<>();
        for (int[] p: people) list.add(p[1], p);
        return list.toArray(new int[0][2]);
    }
    
    private void quickSort(int[][] arr, int left, int right) {
        if (left >= right) return;
        int pivot = partition(arr, left, right);
        quickSort(arr, left, pivot - 1);
        quickSort(arr, pivot + 1, right);
    }
    
    private int partition(int[][] arr, int l, int r) {
        int[] pivot = arr[l];
        while (l < r) {
            while (l < r) {
                if (arr[r][0] > pivot[0] || arr[r][0] == pivot[0] && arr[r][1] < pivot[1]) {
                    arr[l++] = arr[r];
                    break;
                }
                r--;
            }
            while (l < r) {
                if (arr[l][0] < pivot[0] || arr[l][0] == pivot[0] && arr[l][1] > pivot[1]) {
                    arr[r--] = arr[l];
                    break;
                }
                l++;
            }
        }
        arr[l] = pivot;
        return l;
    }
}
```
```C []
/**
 * Return an array of arrays of size *returnSize.
 * The sizes of the arrays are returned as *returnColumnSizes array.
 * Note: Both returned array and *columnSizes array must be malloced, assume caller calls free().
 */
int partition(int** arr, int l, int r)
{
    int *pivot = arr[l];
    while (l < r)
    {
        while (l < r)
        {
            if (arr[r][0] > pivot[0] || arr[r][0] == pivot[0] && arr[r][1] < pivot[1])
            {
                arr[l++] = arr[r];
                break;
            }
            r--;
        }
        while (l < r)
        {
            if (arr[l][0] < pivot[0] || arr[l][0] == pivot[0] && arr[l][1] > pivot[1])
            {
                arr[r--] = arr[l];
                break;
            }
            l++;
        }
    }
    arr[l] = pivot;
    return l;
}

void quickSort(int** arr, int left, int right)
{
    if (left >= right) return;
    int pivot = partition(arr, left, right);
    quickSort(arr, left, pivot - 1);
    quickSort(arr, pivot + 1, right);
}

int** reconstructQueue(int** people, int peopleSize, int* peopleColSize, int* returnSize, int** returnColumnSizes)
{
    quickSort(people, 0, peopleSize - 1);
    int **list = (int **)malloc(peopleSize * sizeof(int *));
    *returnSize = peopleSize;
    *returnColumnSizes = (int *)malloc(peopleSize * sizeof(int));
    for (int i = 0; i < peopleSize; i++)
    {
        int k = people[i][1];
        memmove(&list[k + 1], &list[k], (i - k) * sizeof(int *));
        list[k] = (int *)malloc(sizeof(int) * 2);
        list[k][0] = people[i][0];
        list[k][1] = people[i][1];
        (*returnColumnSizes)[i] = 2;
    }
    return list;
}
```
我劝年轻人耗子尾汁,要讲 wood

### 代码

```java []
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
        List<int[]> list = new LinkedList<>();
        for (int[] p: people) list.add(p[1], p);
        return list.toArray(new int[0][2]);
    }
}
```
```C []
/**
 * Return an array of arrays of size *returnSize.
 * The sizes of the arrays are returned as *returnColumnSizes array.
 * Note: Both returned array and *columnSizes array must be malloced, assume caller calls free().
 */
int cmp(const void* a, const void* b)
{
    int *ap = *(int **)a, *bp = *(int **)b;
    return ap[0] == bp[0] ? ap[1] - bp[1] : bp[0] - ap[0];
}

int** reconstructQueue(int** people, int peopleSize, int* peopleColSize, int* returnSize, int** returnColumnSizes)
{
    qsort(people, peopleSize, sizeof(int *), cmp);
    int **list = (int **)malloc(peopleSize * sizeof(int *));
    *returnSize = peopleSize;
    *returnColumnSizes = (int *)malloc(peopleSize * sizeof(int));
    for (int i = 0; i < peopleSize; i++)
    {
        int k = people[i][1];
        memmove(&list[k + 1], &list[k], (i - k) * sizeof(int *));
        list[k] = (int *)malloc(sizeof(int) * 2);
        list[k][0] = people[i][0];
        list[k][1] = people[i][1];
        (*returnColumnSizes)[i] = 2;
    }
    return list;
}
```

塔姆说我是乱写的,我可不是乱写的啊

### 代码

```java
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        TreeMap<Integer, TreeSet<Integer>> map = new TreeMap<>();
        for (int[] p: people) {
            map.computeIfAbsent(p[0], z -> new TreeSet<>()).add(p[1]);
        }
        List<int[]> list = new ArrayList<>();
        for (int k: map.descendingKeySet()) {
            for (int v: map.get(k)) {
                list.add(v, new int[] {k, v});
            }
        }
        return list.toArray(new int[0][2]);
    }
}
```

for 循环,库函数,训练有素, you bear lie

### 代码

```java
class Solution {
    static final int BASE = 1048576;
    
    public int[][] reconstructQueue(int[][] people) {
        int len = people.length;
        if (len == 0) return new int[0][0];
        long[] aux = new long[len];
        for (int i = 0; i < len; i++) {
            aux[i] = ((long) people[i][0] << 20) + BASE - 1 - people[i][1];
        }
        Arrays.sort(aux);
        List<int[]> list = new ArrayList<>();
        for (int i = len - 1; i >= 0; i--) {
            int a = (int) (aux[i] >> 20);
            int b = (int) (BASE - 1 - aux[i] % BASE);
            list.add(b, new int[] {a, b});
        }
        return list.toArray(new int[0][2]);
    }
}
```

谢谢朋友们
