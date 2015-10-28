#!/usr/bin/env python
# -*- coding: utf-8 -*-

from communs import util

def integration(interval):

    result = 0.0
    a = interval[0]
    b = interval[1]
    for i in range(0, len(interval)-2):
        med = (a + b)/2.0
        result += abs(a - b)/3.0 * (util.f(a) + 4.0*util.f(med) + util.f(b))
        a = b
        b = interval[i+2]

    return result/2

if __name__ == "__main__":
    a = -2.0
    b = 1.0
    i = 0
    t = 20.0
    d = abs(a - b)
    values = []

    for i in range(1, 20):
        j = 0
        interval = []
        while j <= i:
            interval.append(j * d/i + a)
            j += 1

        values.append((i, integration(interval)))

    iteration  = [i[0] for i in values]
    aprox      = [i[1] for i in values]

    util.plotChart('1_3_Simpson', aprox, [], iteration)