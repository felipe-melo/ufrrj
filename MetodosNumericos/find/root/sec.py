#!/usr/bin/env python
# -*- coding: utf-8 -*-

from communs import util

def sec(x0, x1):
    n = 0
    f0 = util.f(x0)
    f1 = util.f(x1)
    x = 0.0
    approximation = []
    roots = []
    while n < util.MAX:
        print n
        x = x1 - (x1 - x0) * f1 / (f1 - f0)
        fx = util.f(x)
        approximation.append(fx)
        roots.append(x)
        n += 1
        if (fx == 0 or abs(x - x1) < util.EPSON):
            return approximation, roots, range(1, n+3)
        x0 = x1
        x1 = x
        f0 = util.f(x0)
        f1 = util.f(x1)

    return approximation, roots, range(1, n+3)

if __name__ == "__main__":
    x0 = -10.0
    x1 = 5.0
    approx, roots, iteration = sec(x0, x1)
    util.plotChart('Secante', approx, roots, iteration)
