#!/usr/bin/env python
# -*- coding: utf-8 -*-

from communs import util

def newtonRaphson(x0):
    n = 0
    x = x0
    approximation = []
    roots = []
    while n < util.MAX:
        xAux = x - util.f(x)/ util.f_(x)
        fxAux = util.f(xAux)
        approximation.append(abs(fxAux))
        roots.append(xAux)
        n += 1
        if (fxAux == 0 or abs(x - xAux) < util.EPSON):
            return approximation, roots, range(1, n+3)
        x = xAux
    return approximation, roots, range(1, n+3)

if __name__ == "__main__":
    x0 = -10.0
    approx, roots, iteration = newtonRaphson(x0)
    print roots
    util.plotChart('newton_raphson', approx, roots, iteration)
