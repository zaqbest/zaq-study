## 最优解

### 整体一定是先下降，再上升的样子
![](https://pic.zaqbest.com/i/2022/12/22/63a3cc541c3e0.png)

### 考虑最低点，可能不重合的情况
0..i是下降区间  
i+1..N-1是上升区间
![](https://pic.zaqbest.com/i/2022/12/22/63a3cc5349ebd.png)

### 具体实现
通过dp来实现，dp[i]指的是从0..i的总成本
![](https://pic.zaqbest.com/i/2022/12/22/63a3cc55b7416.png)


## 动态规划（能学习到东西）

![](https://pic.zaqbest.com/i/2022/12/29/63acf0d8e60cd.png)

现在来到index=8的位置，pre已经变成了3的，并且pre已经被前一个位置搞定，
对与index=8来说，可以把值变成0~8, 然后看index=9~index=N-1的成本
![](https://pic.zaqbest.com/i/2022/12/29/63acf0d94bbd2.png)

