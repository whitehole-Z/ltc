![image.png](https://pic.leetcode-cn.com/1601971634-zqPJxS-image.png)


一句话题解：做对这道题需要熟悉快速排序的 partition 过程。

---

说明：使用库函数排序和手写计数排序都可以完成这道问题，这里省略。



## 什么是 partition ？

我们在学习 **快速排序** 的时候知道，可以选择一个标定元素（称为 `pivot` ，一般而言随机选择），然后通过一次扫描，把数组分成三个部分：

+ 第 1 部分严格小于 `pivot` 元素的值；
+ 第 2 部分恰好等于 `pivot` 元素的值；
+ 第 3 部分严格大于 `pivot` 元素的值。

第 2 部分元素就是排好序以后它们应该在的位置，接下来只需要递归处理第 1 部分和第 3 部分的元素。

经过一次扫描把整个数组分成 *3* 个部分，正好符合当前问题的场景。写对这道题的方法是：把循环不变量的定义作为注释写出来，然后再编码。

---

## 复习 partition

说明：这部分与本题解法无直接关系，直接跳过不影响后续内容的阅读。

下面的动画，从一个中间的状态开始演示，通过循环变量 `i` 以及设置两个边界变量 `lt` 和 `gt` 把一个数组分成 *3* 个部分。

 ![0075-2.png](https://pic.leetcode-cn.com/69a54f276ed2d098b5b6b8e86f139ab6e332f76f0f5e83e3eebcec8f2ff6f80d-0075-2.png) ![0075-3.png](https://pic.leetcode-cn.com/7d752cdd1a3f06cfd4b921b8e7922b47521b533fcadc4fffab681250be1e41e7-0075-3.png) ![0075-4.png](https://pic.leetcode-cn.com/3826dc4466abdbca78668133da7551bdd03d04c0a3c75f70a8b4358eba7d7e09-0075-4.png) ![0075-5.png](https://pic.leetcode-cn.com/f18e13380341a759e8151df04dc4639957e3803d0c5e796305d46974ccc171a5-0075-5.png) ![0075-6.png](https://pic.leetcode-cn.com/862d82c808f982437dddf69327aa23cdc612eb949ec848bd070365210f889827-0075-6.png) ![0075-7.png](https://pic.leetcode-cn.com/69da7dd744d94614c9ce9df37d96a1b417e56568759519c93bbcfabc14c74432-0075-7.png) ![0075-8.png](https://pic.leetcode-cn.com/58ab502607ba45681c8d7aa5e43a6cc888fe179e8cd7989ecaf2a008be7ff381-0075-8.png) ![0075-9.png](https://pic.leetcode-cn.com/d643e1c7d6515d213b1610ef73d30297fc39c086487ef05bdcbe78c8e48380a1-0075-9.png) ![0075-10.png](https://pic.leetcode-cn.com/f138531d8708047785b9aebc9d406de6beca06cab7e6cef48f3613851a6271ad-0075-10.png) ![0075-11.png](https://pic.leetcode-cn.com/8be216a34d760e43234fe3153358c73d29ec8689f356fef24a1b70b55fd18887-0075-11.png) ![0075-12.png](https://pic.leetcode-cn.com/830cb54b87da9384359021671467e51e4b0f27da6ab4e58792936d9094da2b53-0075-12.png) ![0075-13.png](https://pic.leetcode-cn.com/687191ffac82572663ba0d5923f45e4a9c439c9534a0d7b6d969fe27e021952f-0075-13.png) ![0075-14.png](https://pic.leetcode-cn.com/a0e945dba178bb5d74f1b471aacf169316a418bd858172ff386ba540d3c52764-0075-14.png) 


以下给出不同的写法，循环不变量的定义写在了注释中。使用「循环不变量」编码是为了便于我们处理细节，也方便他人阅读代码。

---

## 循环不变量定义 1

**循环不变量**：声明的变量在遍历的过程中需要保持定义不变。

### 设计循环不变量的原则

说明：设计循环不变量的原则是 **不重不漏**。

+ `len` 是数组的长度；
+ 变量 `zero` 是前两个子区间的分界点，一个是闭区间，另一个就必须是开区间；
+ 变量 `i` 是循环变量，一般设置为开区间，表示 `i` 之前的元素是遍历过的；
+ `two` 是另一个分界线，我设计成闭区间。

如果循环不变量定义如下：

+ 所有在子区间 `[0, zero)` 的元素都等于 `0`；
+ 所有在子区间 `[zero, i)` 的元素都等于 `1`；
+ 所有在子区间 `[two, len - 1]` 的元素都等于 `2`。


于是编码要解决以下三个问题：

+ 变量初始化应该如何定义；
+ 在遍历的时候，是先加减还是先交换；
+ 什么时候循环终止。


**处理这三个问题，完全看循环不变量的定义**。

+ 编码的时候，`zero` 和 `two` 初始化的值就应该保证上面的三个子区间全为空；
+ 在遍历的过程中，「下标先加减再交换」、还是「先交换再加减」就看初始化的时候变量在哪里；
+ 退出循环的条件也看上面定义的循环不变量，在 `i == two` 成立的时候，上面的三个子区间就正好 **不重不漏** 地覆盖了整个数组，并且给出的性质成立，题目的任务也就完成了。



**参考代码 1**：

```Java []
import java.util.Arrays;


public class Solution {

    public void sortColors(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return;
        }

        // all in [0, zero) = 0
        // all in [zero, i) = 1
        // all in [two, len - 1] = 2
        
        // 循环终止条件是 i == two，那么循环可以继续的条件是 i < two
        // 为了保证初始化的时候 [0, zero) 为空，设置 zero = 0，
        // 所以下面遍历到 0 的时候，先交换，再加
        int zero = 0;

        // 为了保证初始化的时候 [two, len - 1] 为空，设置 two = len
        // 所以下面遍历到 2 的时候，先减，再交换
        int two = len;
        int i = 0;
        // 当 i == two 上面的三个子区间正好覆盖了全部数组
        // 因此，循环可以继续的条件是 i < two
        while (i < two) {
            if (nums[i] == 0) {
                swap(nums, i, zero);
                zero++;
                i++;
            } else if (nums[i] == 1) {
                i++;
            } else {
                two--;
                swap(nums, i, two);
            }
        }
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
```
```Python []
from typing import List


class Solution:
    def sortColors(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """

        # all in [0, zero) = 0
        # all in [zero, i) = 1
        # all in [two, len - 1] = 2

        def swap(nums, index1, index2):
            nums[index1], nums[index2] = nums[index2], nums[index1]

        size = len(nums)
        if size < 2:
            return

        zero = 0
        two = size

        i = 0

        while i < two:
            if nums[i] == 0:
                swap(nums, i, zero)
                i += 1
                zero += 1
            elif nums[i] == 1:
                i += 1
            else:
                two -= 1
                swap(nums, i, two)
```
```C++ []
#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    void sortColors(vector<int> &nums) {
        int size = nums.size();
        if (size < 2) {
            return;
        }

        // all in [0, zero) = 0
        // all in [zero, i) = 1
        // all in [two, len - 1] = 2

        int zero = 0;
        int two = size;
        int i = 0;
        while (i < two) {
            if (nums[i] == 0) {
                swap(nums[zero], nums[i]);
                zero++;
                i++;
            } else if (nums[i] == 1) {
                i++;
            } else {
                two--;
                swap(nums[i], nums[two]);
            }
        }
    }
};
```

**复杂度分析**：

+ 时间复杂度：*O(N)*，这里 *N* 是输入数组的长度；
+ 空间复杂度：*O(1)*。


## 循环不变量定义 2

**参考代码 2**：

```Java []
public class Solution {

    public void sortColors(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return;
        }
        // all in [0, zero] = 0
        // all in (zero, i) = 1
        // all in (two, len - 1] = 2

        // 为了保证初始化的时候 [0, zero] 为空，设置 zero = -1，
        // 所以下面遍历到 0 的时候，先加，再交换
        int zero = -1;

        // 为了保证初始化的时候 (two, len - 1] 为空，设置 two = len - 1
        // 所以下面遍历到 2 的时候，先交换，再减
        int two = len - 1;
        int i = 0;
        // 当 i == two 的时候，还有一个元素还没有看，
        // 因此，循环可以继续的条件是 i <= two
        while (i <= two) {
            if (nums[i] == 0) {
                zero++;
                swap(nums, i, zero);
                i++;
            } else if (nums[i] == 1) {
                i++;
            } else {
                swap(nums, i, two);
                two--;
            }
        }
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
```
```Python []
from typing import List


class Solution:
    def sortColors(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """

        # all in [0, zero] = 0
        # all in (zero, i) = 1
        # all in (two, len - 1] = 2

        def swap(nums, index1, index2):
            nums[index1], nums[index2] = nums[index2], nums[index1]

        size = len(nums)
        if size < 2:
            return

        zero = -1
        two = size - 1

        i = 0

        while i <= two:
            if nums[i] == 0:
                zero += 1
                swap(nums, i, zero)
                i += 1
            elif nums[i] == 1:
                i += 1
            else:
                swap(nums, i, two)
                two -= 1
```
```C++ []
#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    void sortColors(vector<int> &nums) {
        int size = nums.size();
        if (size < 2) {
            return;
        }

        // all in [0, zero] = 0
        // all in (zero, i) = 1
        // all in (two, len - 1] = 2

        int zero = -1;
        int two = size - 1;
        int i = 0;

        while (i <= two) {
            if (nums[i] == 0) {
                zero++;
                swap(nums[zero], nums[i]);
                i++;
            } else if (nums[i] == 1) {
                i++;
            } else {
                swap(nums[i], nums[two]);
                two--;
            }
        }
    }
};
```

**复杂度分析**：（同参考代码 1）

说明：这种做法是我在 Java 的 JDK 的源码中 `Arrays.sort()` 中学到的。

![image.png](https://pic.leetcode-cn.com/0ff67b77154bcea4b85faeceb23b80062f82c20c137a6d8332ad068e624531f8-image.png)

我的体会是：

+ 编码者应该在代码中使用注释表达这段代码编写的算法思想，提醒自己也方便他人。
+ 但是源代码中类似 `++k <= great` 和 `a[++left] >= a[left - 1]` 这样的代码建议不要写，会给阅读者带来理解上的障碍，这种做法也是《阿里巴巴 Java 开发手册》中不推荐的，理由是：变量的值发生变化是一个很重要的逻辑，应该单独成为一行，否则不利于调试和以后定位问题，这种语法糖我个人认为应该禁止使用。

![image.png](https://pic.leetcode-cn.com/fcc65d9c5f95f62a2d9f70c01671277be3eb2a2197f0682e954f14f9b066d60f-image.png)

---

## 相关问题

下面这两个问题可以用于复习 partition 的过程。

+ 「力扣」第 215 题：[数组中的第 K 个最大元素](https://leetcode-cn.com/problems/kth-largest-element-in-an-array/)（中等）
+ 「力扣」第 451 题：[根据字符出现频率排序](https://leetcode-cn.com/problems/sort-characters-by-frequency/)（中等）


说明：这一题参与比较的是字符，题目中虽然没有给出字符的范围，但是通过测试可以知道，测试数据的字符不超过 *128* 个，符合有大量重复元素出现的情况，可以使用 partition 的过程对输入数据进行排序。

**「力扣」第 451 题参考代码**：

手写快速排序，如果很熟悉 partition，代码只需要稍作修改即可。

```Java []
import java.util.Random;

public class Solution {

    private int[] freq;

    private static final Random RANDOM = new Random();

    public String frequencySort(String s) {
        // 先转换为字符数组，以避免 charAt() 方法每次都检查下标有效性
        char[] charArray = s.toCharArray();
        // 用 128 是测试出来的，说明题目中的字符只有 a-zA-Z
        freq = new int[128];
        for (char c : charArray) {
            freq[c]++;
        }

        int len = charArray.length;
        quickSort(charArray, 0, len - 1);
        return new String(charArray);
    }

    private void quickSort(char[] charArray, int left, int right) {
        if (left >= right) {
            return;
        }
        int randomIndex = left + RANDOM.nextInt(right - left + 1);
        swap(charArray, randomIndex, left);

        // 循环不变量定义
        // all in [left + 1, lt] 的频数 > pivot 的频数
        // all in [lt + 1, i) 的频数 = pivot 的频数
        // all in [gt, right] 的频数 < pivot 的频数
        int pivot = charArray[left];
        int lt = left;
        int gt = right + 1;

        int i = left + 1;
        while (i < gt) {
            // 只需要在这句话外面套一层 freq [] ，其它逻辑和快速排序一样
            if (freq[charArray[i]] > freq[pivot]) {
                lt++;
                swap(charArray, i, lt);
                i++;
            } else if (charArray[i] == pivot) {
                i++;
            } else {
                gt--;
                swap(charArray, i, gt);
            }
        }
        swap(charArray, left, lt);
        // 注意这里，大大减少了分治的区间
        quickSort(charArray, left, lt - 1);
        quickSort(charArray, gt, right);
    }

    private void swap(char[] charArray, int index1, int index2) {
        char temp = charArray[index1];
        charArray[index1] = charArray[index2];
        charArray[index2] = temp;
    }
}
```

下面的问题用于复习循环不变量。

+ 「力扣」第 26 题：[删除排序数组中的重复项](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array)（简单）
+ 「力扣」第 27 题：[移除元素](https://leetcode-cn.com/problems/remove-element/)（简单）
+ 「力扣」第 283 题：[移动零](https://leetcode-cn.com/problems/move-zeroes)（简单）

---

## 参考资料

在《算法导论》里大量使用了「循环不变量」这个概念说明和证明问题。但「循环不变量」并不是一个很高深的概念，其实我们很多时候，在编写代码的过程中都在不自觉地维护了变量的定义。「循环不变量」只是一个学术化的名字而已，设计清楚「循环不变量」，可以帮助我们写出正确的代码。

+ 《算法导论》第 2.1 节 插入排序
+ 《算法导论》第 2.3.1 节 分治法
+ 《算法导论》第 6.3 节 建堆
+ 《算法导论》第 7.1 节 快速排序的描述

事实上，一个广泛应用「循环不变量」概念的算法就是 **二分查找**，二分查找有些时候不能写对，与不能维持循环不变量的定义有很大的关系。