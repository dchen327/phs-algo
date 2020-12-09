'''
Given N distinct integers, how many triples sum to exactly zero?
Write a program, Better3Sum_YI.java, so its order of growth is N^2 log N.

Author: David Chen
Python 3.8.2 - 12/9/20
'''


def binary_search(a, x):
    """ Returns the index of x in a if x is in a, else returns None 
        By returning the index as well, our code works for duplicates.
    """
    l, r = 0, len(a)
    while l < r:
        m = (l + r) // 2
        if a[m] == x:
            return m
        if a[m] < x:
            l = m + 1  # we don't want infinite loops when l = r - 1
        else:
            r = m


def three_sum(a):
    """ Returns the number of triples in a that sum to 0 """
    t = 0
    a = sorted(a)
    for i in range(len(a)):
        for j in range(i + 1, len(a)):
            third_val = -a[i] - a[j]
            k = binary_search(a, third_val)  # idx of third value
            # make sure it's a new element (code will work even if a has duplicates)
            if k and k > j:
                t += 1
                print(a[i], a[j], a[k])
    return t


if __name__ == "__main__":
    a = [30, -40, -20, -10, 40, 0, 10, 5]
    print('a:', a)
    print('Number of triples that sum to 0:', three_sum(a))
    print()
    a = [2, 3, 5, 9, -2, -8, -4, -3, -1, 10, 11, -5]
    print('a:', a)
    print('Number of triples that sum to 0:', three_sum(a))
    print()

# the output
'''
a: [30, -40, -20, -10, 40, 0, 10, 5]
-40 0 40
-40 10 30
-20 -10 30
-10 0 10
Number of triples that sum to 0: 4

a: [2, 3, 5, 9, -2, -8, -4, -3, -1, 10, 11, -5]
-8 -3 11
-8 -2 10
-8 -1 9
-8 3 5
-5 -4 9
-5 2 3
-4 -1 5
-3 -2 5
-2 -1 3
Number of triples that sum to 0: 9
'''
