# Source from: 
# http://members.cbio.mines-paristech.fr/~thocking/change-tutorial/RK-CptWorkshop.html
# Introduction to optimal changepoint detection algorithms
# This code is tasks in the workshop
# Install packages ----
if(!require(changepoint)){
  install.packages('changepoint')
}
library(changepoint)
if(!require(changepoint.np)){
  install.packages('changepoint.np')
}
library(changepoint.np)

# Task: Nile Data----
data(Nile)
ts.plot(Nile)
# Use the cpt.mean function to see if there is evidence for a change in mean in the Nile river data. 
# If you identify a change, where is it and what are the pre and post change means?
nile.amoc = cpt.mean(Nile) 
cpts(nile.amoc) # 28 change occured at 1899
plot(nile.amoc) 

# Task: FTSE100----
# Yahoo Finance data of daily returns from FTSE100 index from 2 Apr 1984 to 13 Sep 2012
data(ftse100) # two columns, date and value
plot(ftse100,type='l',xlab='Date',ylab='Daily Return')
# Use the cpt.var function to see if there is evidence for changes in variance in the FTSE100 data. 
# If you identify changes, where are they and what are the variances in each segment? 
# Try changing your penalty value, which segmentation do you prefer?
library(zoo)
z <- read.zoo(ftse100, format = "%Y-%m-%d")
ftse <- as.ts(z)
#ftse.man = cpt.var(ftse, method = "PELT", penalty = "Manual", pen.value = "2*log(n)")
ftse.man = cpt.var(ftse100[,2], method = "PELT", penalty = "Manual", pen.value = "2*log(n)")
# Task: HC1 ----
# data from NCBI on G+C content within part of Human Chromosome 1
data(HC1)
ts.plot(HC1)
#Use the cpt.meanvar function to identify regions with different C+G content. 
#Try changing your penalty value, which segmentation do you prefer?
HC1.binseg=cpt.meanvar(HC1,test.stat='Exponential',method='BinSeg',Q=10,penalty="SIC")
cpts(HC1.binseg)
param.est(HC1.binseg)
plot(HC1.binseg,cpt.width = 3, cpt.col = 'blue')

# Task: CROPS using FTSE100 ----
ftse.crops = cpt.var(ftse100[,2], method = "PELT", penalty = "CROPS", pen.value = c(5,500))
cpts.full(ftse.crops) #cpts.full to get the range of segmentations
# retrieve the penalty boundary points: 
pen.value.full(ftse.crops)
# when using BinSeg or CROPS as a range of changepoints are given as output we can use additional arguement in the plot generic (allow us to select how many changes we want in the plot)
plot(ftse.crops, ncpts=5) 
# construct diagnostic plot:
plot(v1.crops, diagnostic = TRUE) 

# Task: cpt.np HeartRate
data("HeartRate")
# Use one of the non-parametric functions to see if there is evidence for changes in heart rate
out <- cpt.np(HeartRate, method="PELT",minseglen=2, nquantiles =4*log(length(data)))
cpts(out)
plot(out)

# Consolidating Task----
# Download the ratings for the following TV shows from the IMDB and analyze the series using some of the techniques you have learnt from today. 
# For each series, do you identify any changes? 
# Are the assumptions you are making valid? 
# What effect might any invalid assumptions have on the inference?

# Doctor Who
# Grey’s Anaytomy
# Mistresses
# The Simpsons
# Top Gear