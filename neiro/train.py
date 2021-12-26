import os

path = 'data/obj/'

imgList = os.listdir('inpit')
print(imgList)

file = open('train.txt','w')

for img in imgList:
    imgPath = path + img + "\n"
    file.write(imgPath)

file.Close()
