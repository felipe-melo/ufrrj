#!/usr/bin/env python
# -*- coding: utf-8 -*-

from communs import util

def bisection(a, b):
    n = 0
    approximation = []
    roots = []
    while n < util.MAX:
        c = (a + b) / 2
        fc = util.f(c)
        fa = util.f(a)
        approximation.append(abs(fc))
        roots.append(c)
        n += 1
        if fc == 0 or abs(b - a) / 2 < util.EPSON:
            return approximation, roots, range(1, n+3)
        if fa * fc > 0:
            a = c
        else:
            b = c

    return approximation, roots, range(1, n+3)

if __name__ == "__main__":

    a = -10.0
    b = 5.0

    approx, roots, iteration = bisection(a, b)

    util.plotChart('Bisection', approx, roots, iteration)
