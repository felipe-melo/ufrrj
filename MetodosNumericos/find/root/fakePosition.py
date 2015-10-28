#!/usr/bin/env python
# -*- coding: utf-8 -*-

from communs import util


def fakePosition(a, b):
    n = 0
    approximation = []
    roots = []
    while n < util.MAX:
        fa = util.f(a)
        fb = util.f(b)
        if (fb - fa) == 0:
            return approximation, roots, range(1, n+3)
        c = b - ((fb * (b - a)) / (fb - fa))
        fc = util.f(c)
        approximation.append(abs(fc))
        roots.append(c)
        n += 1
        print c, fc
        if abs(fc) <= util.EPSON or abs(b - a) <= util.EPSON:
            return approximation, roots, range(1, n+3)
        if fa * fc > 0:
            a = c
        else:
            b = c

    return approximation, roots, range(1, n+3)

if __name__ == "__main__":

    a = -10.0
    b = 5.0

    approx, roots, iteration = fakePosition(a, b)
    util.plotChart('fake_position', approx, roots, iteration)