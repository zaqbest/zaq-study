假设
arr1:10个数
arr2:17个数

1) k >1 && k <= 10

取arr1的前7个数和arr2的前7个数的中位数

2) k > 10 && k <= 17

例如求第15小

![](https://assets.zaqbest.com/2022/04/30/626cbbef023b3.jpg)

![](https://assets.zaqbest.com/2022/04/30/626cbbee2258c.jpg)

3) k > 17 && k <= 27

比如求第23小

![](https://assets.zaqbest.com/2022/04/30/626cbbefe38bd.jpg)

![](https://assets.zaqbest.com/2022/04/30/626cbbf0d1053.jpg)

**特殊处理**

6是不是13小，判断6是不是大于17'，如果是直接返回

13’是不是13小，13‘大于等于10，直接返回

再处理arr1[7,8,9,10]和arr1[14',15',16',17']