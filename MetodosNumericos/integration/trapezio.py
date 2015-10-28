#!/usr/bin/env python
# -*- coding: utf-8 -*-

from communs import util

def trapezio(interval):
    result = 0
    a = interval[0]
    b = interval[1]
    for i in range(0, len(interval)-2):
        result += abs(a - b)/2 * (util.f(a) + util.f(b))
        a = b
        b = interval[i+2]

    return result

if __name__ == "__main__":
    a = -2.0
    b = 1.0
    i = 0
    t = 20
    d = abs(a - b)
    values = []
    for i in range(1, t):
        interval = []
        j = 0
        while j <= i:
            interval.append(j * d/i + a)
            j += 1

        values.append((i, trapezio(interval)))

    iteration  = [i[0] for i in values]
    aprox      = [i[1] for i in values]

    util.plotChart('trapezio', aprox, [], iteration)

    util.plotFuncChart('x^4 + 2x^3 - 13x^2 - 14x + 24', util.f, -6, 5)