```java
List<Integer> values = Arrays.asListt(1,2,3,4,5,6);
```

I want to use the same list and I want to print the values by doubling all the values. What we can do is we can double all the values with the help of enhanced for loop. 

```java
List<Integer> values = Arrays.asListt(1,2,3,4,5,6);

int result = 0;
for(int i : values){
    result = result + i;
}
System.out.prinln(result);
```

Now we're adding all the values which will be stored in the result and the output will be 21. 

What we're doing here, we are achieving something called as mutation. Because the value of result is continuously changed for six times in fact seven times, first was zero then it will be changed for fix times. Every time we mutate our value, it makes system unstable maybe not unstable but we can not achieve concurrency here. To make our system very fast  we need to achieve concurrency, to achieve concurrency we have to reduce the mutation. Even if I multiply by two, we are achieving one more mutation. If we run that the answer will be 42. The question is how to ignore mutation , how can we write a code without achieving mutation so what we can do is  in Java 8 we got something called as Stream Api. Stream Api  says what you want to print values, go ahead bring the values. 

```java
System.out.prinln(values);
```

but we don't want to print the values, we want to print stream of values. Stream convert your collection into a stream.

```java
System.out.prinln(values.stream());
```

We got the object of stream. This stream method returns us the object of stream interface 

<img src="C:\Users\ofn2nvu\AppData\Roaming\Typora\typora-user-images\image-20211231191848996.png" alt="image-20211231191848996" style="zoom:200%;" />

It has multiple methods and one of the methods we have to use here is to double the values we'll use something else map. Map needs an object of function but instead of using function object here we can use lambda expression.  We can simple say every value of I for the all the values of this values collection take one value at a time which is I and apply the operation which is i*2. This map will give us a stream of values and that stream of values we need to find the addition of all the values. This reduce method will use object of binary operator but instead of i can create an object we can simply use a 0,0 the initial value so it will take two parameters then is the initial value and then the binary operator, we can use two values here one will be (c,e) -> c+e

```java
List<Integer> values = Arrays.asList(1,2,3,4,5,6);
System.out.println(values.stream().map(i -> i * 2).reduce(0,(c,e) ->c+e));
```

```java
values.stream().map(i ->i*2).reduce(0,(c,e)->c+e);

values.stream().map(i ->i*2).reduce(0,(c,e)->Integer.sum(c,e));

values.stream().map(i ->i*2).reduce(0,Integer::sum);


```

