# MapWithHashing

## Objectives
1. Familiarity with writing a kernel class for a new type and its kernel operations (Map layered on an array of Maps, using hashing).
2. Familiarity with developing and using specification-based test plans.

## The Problem
The problem is to complete and carefully test implementations of all the constructors and kernel methods defined in interface MapKernel, building the data structure representing a Map object by layering it on top of an array of (perhaps surprisingly) Map itself. The algorithmic approach is to use hashing to immediately narrow every search to a much smaller Map.

There is nothing circular about this! The Map entries (buckets) in the array (hash-table) simply have a different implementation than the one you are writing code for here. When constructing the hash-table (in private method createNewRep), you will set each bucket to be a Map2 object. (You could use any other Map implementation other than Map4.)

The other interesting thing to note about this assignment is how little code you have to write for the bodies of the Map4 kernel methods. Hashing, in other words, offers a tremendous performance improvement for very little code. On the other hand, as in all kernel implementations, you have to get this code all correct, for if even one of the method bodies is wrong then other methods might appear not to work correctly during testing. This makes debugging a challenge. There are several reasons for this. First, the kernel methods have asserts to check the preconditions in order to prevent you from executing buggy test cases. These asserts call your own code to do the checking. So, for example, the first time you add something in a test case, the method starts by asking whether the key value is already defined in the Map object; and this means that, if your code for hasKey doesn't work correctly, you might get a violated assertion while appearing to test add. Second, if your test cases use either toString or equals to check the results, these methods can essentially call any of your kernel methods. Third, if your code for any method fails to make the representation invariant true at the time it returns, then the next method called in the test script might break; but the problem is actually with the previous method.
