
import numpy as np
import cv2
import matplotlib.pyplot as plt
from PIL import image
import io
import base64

def draw(expense, income):
    fig = plt.figure()

    labels = ["expense", "income"]
    pie = plt.pie([expense,income], labels = labels)

    pie.canvas.draw()



