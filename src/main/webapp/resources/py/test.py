#-*- coding: utf-8 -*-

import sys
import numpy as np
import cv2
import pytesseract


def ocr(langage, path):
    src = cv2.imread(path)
    return pytesseract.image_to_string(src, lang=langage)

print(ocr(sys.argv[1], sys.argv[2]))
