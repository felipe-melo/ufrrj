#!/usr/bin/env python
# -*- coding: utf-8 -*-

from communs import util

def squareMin(points, a, b, iterations):
    n = 0

    while n < iterations:
        jTemp = util.J(points, a, b)

        tempA = util.J_a(points, a, b) * 0.0001
        tempB = util.J_b(points, a, b) * 0.0001

        a -= tempA
        b -= tempB

        j = util.J(points, a, b)

        if abs(j - jTemp) < util.EPSON:
            return a, b

        n += 1

    return a, b

if __name__ == "__main__":
    points = [(1, 1), (2, 3), (3, 2), (4, 5), (5, 3)]
    a = 0.0
    b = 0.0
    iterations = 100
    af, bf = squareMin(points, a, b, iterations)

    print af, bf

    #util.plotChart("curve_fitting", xs, line, range(1, len(points)+3))