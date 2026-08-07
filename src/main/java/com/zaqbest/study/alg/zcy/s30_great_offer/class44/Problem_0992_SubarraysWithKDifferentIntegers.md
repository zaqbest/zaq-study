## 解法1
两个窗口，窗口1维持k-1种字符，窗口2维持k种字符
示意图如下：
![](https://pic.zaqbest.com/i/2022/12/26/63a9a1f0a0dd8.png)

![](https://pic.zaqbest.com/i/2022/12/26/63a9a1f03ad61.png)

具体例子
![](https://pic.zaqbest.com/i/2022/12/26/63a9a1ef32e04.png)

## 解法2
统计<=k一共有a个子数组， <=k-1总共有a个子数组
那么=k, 就是有a-b个子数组
![](https://pic.zaqbest.com/i/2022/12/26/63a9a1ee726ee.png)