'''
Hadamard matrix. Write a recursive program Hadamard_YI.java that takes a
command-line argument n and plots an N-by-N Hadamard pattern where N = 2n. Do
not use an array. A 1-by-1 Hadamard pattern is a single black square. In
general, a 2N-by-2N Hadamard pattern is obtained by aligning 4 copies of the
N-by-N pattern in the form of a 2-by-2 grid, and then inverting the colors of
all the squares in the lower right N-by-N copy. The N-by-N Hadamard H(N)
matrix is a boolean matrix with the remarkable property that any two rows
differ in exactly N/2 bits. This property makes it useful for designing
error-correcting codes. Here are the first few Hadamard matrices.

Author: David Chen
Python 3.8.2 - 9/24/20
'''

import matplotlib.pyplot as plt
import numpy as np
from matplotlib import colors
from matplotlib.patches import Rectangle


def hadamard(n):
    """ Plot a hadamard matrix for n, where n is a power of 2 
        Uses numpy array concatenation, which is slow
        In the matrix 1 is black, -1 is white
    """
    def recurse(x):
        if x == 1:  # 1x1 square, we return 1 (black)
            return np.array([1])
        else:
            prev = recurse(x // 2)  # previous hadamard matrix
            # create current matrix according to the grid
            # prev | prev
            # ------------
            # prev | -prev
            # hstack and vstack are horizontal and vertical stacks, respectively
            return np.vstack((np.hstack((prev, prev)), np.hstack((prev, -prev))))

    mat = recurse(n)  # call with n as initial value, store result in mat
    fig, ax = plt.subplots(figsize=(10, 10))
    cmap = colors.ListedColormap(['white', 'black'])  # create colormap
    ax.matshow(mat, cmap=cmap)
    plt.xticks([])  # remove axis labels
    plt.yticks([])
    plt.show()


def hadamard2(n):
    """ Plot a hadamard matrix for n, where n is a power of 2 
        Doesn't use any arrays (just directly plots)
    """
    # set up plot stuff
    fig, ax = plt.subplots(figsize=(10, 10))
    ax.set_xticks(range(n + 1))  # set axis ranges
    ax.set_yticks(range(n + 1))
    plt.xticks([])  # remove axis labels
    plt.yticks([])
    ax.set_aspect(aspect=1)  # ensure it's a square and not a rectangle
    # invert y axis so the origin is the top left
    ax.set_ylim(ax.get_ylim()[::-1])

    def recurse(tlx, tly, brx, bry, flag):
        """ Given coords for the top left and bottom right of a square, recursively pass a boolean flag
            to see if we should draw it
        """
        if(tlx + 1 == brx):  # single square (width == 1)
            if flag:  # draw black square
                ax.add_patch(Rectangle((tly, brx - 1), 1, 1, color='black'))
            return  # no need to recurse anymore
        # here's the recursive part:
        # we go in the order of top left, top right, bottom left, bottom right
        # we negate the flag in the bottom right, and we keep the same flag for the rest
        recurse(tlx, tly, (tlx + brx) // 2, (tly + bry) // 2, flag)
        recurse((tlx + brx) // 2, tly, brx, (tly + bry) // 2, flag)
        recurse(tlx, (tly + bry) // 2, (tlx + brx) // 2, bry, flag)
        recurse((tlx + brx) // 2, (tly + bry) // 2, brx,
                bry, not flag)  # invert bottom right

    recurse(0, 0, n, n, True)  # initial case, pass corners of entire matrix
    plt.show()


if __name__ == '__main__':
    # hadamard(16)
    # hadamard2(64)
    pass

for i in range(10):
    name = 'hello'
    print(i)
