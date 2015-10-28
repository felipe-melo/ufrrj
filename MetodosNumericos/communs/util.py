#!/usr/bin/env python
# -*- coding: utf-8 -*-

import pygal

EPSON = 0.0001
MAX = 1000

f = lambda x : x**4 + 2.0 * x**3 -13.0 * x**2 -14.0 * x + 24
f_ = lambda x : 4 * x**3 + 6.0 * x**2 -26.0 * x -14.0

def J(points, a, b):
    n = 0
    retult = 0.0
    while n < len(points):
        retult += pow((a * points[n][0] + b) - points[n][1], 2)
        n += 1

    return retult/len(points)

def J_a(points, a, b):
    n = 0
    result = 0.0
    while n < len(points):
        result += (a * points[n][0] + b - points[n][1]) * points[n][0]
        n += 1

    return result/len(points)

def J_b(points, a, b):
    n = 0
    retult = 0.0
    while n < len(points):
        retult += (a * points[n][0] + b - points[n][1])
        n += 1

    return retult/len(points)

def plotChart(method, approximations, roots, iterations):
    line_chart = pygal.Line()
    line_chart.title = method
    line_chart.x_labels = map(str, iterations)
    line_chart.add('x', roots)
    line_chart.add('f(x)', approximations)
    try:
        line_chart.render_to_file('../../chart/' + method + '_line_chart.svg')
    except IOError:
        line_chart.render_to_file('../chart/' + method + '_line_chart.svg')

def plotFuncChart(method, f, a, b):

    x = []
    y = []
    for i in range(a, b+1):
        x.append(i)
        y.append(f(i))

    line_chart = pygal.StackedLine(fill=True)
    line_chart.title = method
    line_chart.x_labels = map(str, range(a, b+1))
    line_chart.add('(x, y)', y)
    line_chart.render_to_file('../chart/f_line_chart.svg')