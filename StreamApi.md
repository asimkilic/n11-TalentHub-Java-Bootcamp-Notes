In java 8 we can also use functional programing  this is the way you can use it that's **Stream Api**. So just to demonstrate how exactly Stream Api works let's start with an example. So let's say you have a list of values how much value we'll take let's say we take 1000 values. How do we create a list so  we say list and then we must mention they're integer and then new ArrayList.

```java
List<Integer> values = new ArrayList<>();
```

and the way we can put values there are multiple ways of putting the values let's say we are using add method and we want to add 100 values so technically we could use for loop.

```java
List<Integer> values = new ArrayList<>();
for(int i=0; i<=100; i++){
    values.add(i);
}
```

We've got hundred values now, what next? Now we want to print all those values so how do we print it? We can use normal for loop or we can use enhanced for loop right. So let's say If we're using enhanced for loop so we got a list in which we have 100 values so we have added all those values with a for loop and now we are printing it with enhanced for loop. 

```java
List<Integer> values = new ArrayList<>();
for(int i = 0; i <= 100; i++){
    values.add(i);
}
for(int i : values){
    System.out.println(i);
}
```

This is what we know from Collection Api but we also know that we can print those values with the help of *internal iteration*. 

This one of the way is using foreach method right so we can simply use values.forEach() we can mention the lambda expression which is I add odd or we can use the method reference.

```java
List<Integer> values = new ArrayList<>();
for(int i = 0; i <= 100; i++){
    values.add(i);
}
values.forEach(System.out::println);
```

Using method reference we can print all the values so that's quite simple so instead of using enhanced for loop to print the values we can actually use for each method hold on we are not started with Stream yet,now let's convert this into stream. How do we convert it actually. It's very simple we just have to change with one method, earlier we would having values.forEach() this time we'll go for values.stream().forEach().

```java
List<Integer> values = new ArrayList<>();
for(int i = 0; i <= 100; i++){
    values.add(i);
}
values.stream().forEach(System.out::println);
```

So that stream method will convert our normal list into a stream which will have list of values so we have values list which will have some values even in stream you will have values but then question arise **what is the advange?** any way we can use forEach with list and we can use foreach with stream, what's the advantage . We can actually use something called as **parallelStream**. 

```java
List<Integer> values = new ArrayList<>();
for(int i = 0; i <= 100; i++){
    values.add(i);
}
values.parallelStream().forEach(System.out::println);
```

Let's say we have approximately ten thousand values and we want to print all those values with printing we are doing some extra stuff also so what happens is in our machine we'll be having octa-core CPU, we might be having quad-core machine so to work with all the cores we need a thread  and by default we have just one thread. So as a programmer we have to create those threats. Why should we create those threats when we can get those threads for free I mean exactly in fact using threads also we can get for free but this time **you don't have to create threads**, you just have to mention parallel if you make it parallel method automatically it will create multiple threads for us how much depend upon the available cause if we have four cores available it will create four threads.  

So we can use stream and we can use parallelStream that's  one advantage, stream has lots of methods. Now what are those methods actually used for. So if we know in this information age right so in this information age we have lots of data to work with and and that's why we have a concept of big data etc. All this concept we have to process huge amount of data and in stream api we have lots of methods to process this data example, let's say we have list of  values and from those list of values you want to remove some values. We want to only five values or you have list of values in which you only want odd numbers, we can use stream api used to filter on those elements, because in stream we have certain methods like filter.

```java
List<Integer> values = new ArrayList<>();
for(int i = 0; i <= 100; i++){
    values.add(i);
}
values.stream().filter(i -> {
    System.out.println("hi");
	return true;
});
```

 So in this filter we can mention I want odd numbers it will give us only odd numbers. We have a method like map if we want to convert a value into another value let's say we have  list of values we have one to ten  we want to multiply each value we can use map. We want to convert all double value to integer value we can do that, there is  a method class map to int. So in the stream api we have lots of methods using which we can process data but that's not the only advantage we have. We have one more advantage in stream we have two types of methods one is **intermedite methods** and then we have **terminate methods** now what exactly it means let's say we have stream of values so whatever exactly this, we have this watch stream is because it's that a stream of water you know once the value has been used you can not reuse the values. Example if we have list of values and we are doing some stuff with list of values we can use the same list of values multiple times right. We have list of values we have printed those values once we can also print those values second time or third time but what about  stream once we use streams values we can't reuse it.

Let's say we have a method which is filter and then in this filter we are passing a lambda expression.



```java
List<Integer> values = new ArrayList<>();
for(int i = 0; i <= 100; i++){
    values.add(i);
}
values.stream().filter(i -> {
    System.out.println("hi");
	return true;
});
```

If we run this code now, this code will not executed even if we are executing this it will not give we any compile time error. It will run perfectly but we will not get any output it's because this method is intermediate method and this is also **called as lazy evaluation** since we are not doing any stuff we are just filtering the values but we are not printing it then why to even execute that, so stream is lazy if we find the value. Let's say we want to find the first value so there is a method called findFirst, it is like a termination method.

```java
List<Integer> values = new ArrayList<>();
for(int i = 0; i <= 100; i++){
    values.add(i);
}
values.stream().filter(i -> {
    System.out.println("hi");
	return true;
}).findFirst();
```

If we have a findFirst method it will actually find the first value. But findFirst will give us optional value or integer value so what we need is we have to print this into System.out.prinln so that we will get the values that's how it  works. So stream **once use cannot be reused**. If we are using findFirst, it will give us optional but let's we don't want optional. Let's say we have list of five values  and we want to print all odd numbers, quite simple we can filter out the odd numbers but what if in our code we don't have in our values that don't have any odd number what will be the output, nothing. That's why stream api provides us something optional. If we get an optional value and we want to print it we can also use orElse method.

```java
List<Integer> values = new ArrayList<>();
for(int i = 0; i <= 100; i++){
    values.add(i);
}
System.out.println(values.stream().filter(i -> {
    System.out.println("hi");
    return true;
}).findFirst().orElse(0));
```



```java
List<Integer> values = Array.asList(1,2,23,3,4,5,6,11);
Stream<Integer> s = values.stream();
s.forEach(System.out::println); // will work
s.forEach(System.out::println); // exception
```

Let's say we have stream of values and we want to use this the same stream two times can we do it if we use the same stream two times so if I use it for the first time, it will work  we can see the stream of values and if we print all these values it will work but if we use the same method next time for the same stream it will give us exception. It's because once this stream is used  we can not  reuse it, but what about list we can use the list multiple times but not stream so stream provides us some advantages and even this is not a drawback right because we don't want to preserve stream that's not the whole intention here. The intention is just to process data this is like stream of data we want to process that's it. Steam is a new concept in Java 8 which provides us to process huge amount of data it has lots of methods it also has a feature of parallel stream.