# Source from: 
# http://members.cbio.mines-paristech.fr/~thocking/change-tutorial/RK-CptWorkshop.html
# Introduction to optimal changepoint detection algorithms
# Install packages ----
if(!require(changepoint)){
  install.packages('changepoint')
}
library(changepoint)
if(!require(changepoint.np)){
  install.packages('changepoint.np')
}
library(changepoint.np)

# What are Changepoints? ----
  ## Online vs Offline----

# Single Changepoint ----
  ## changepoint R package----  
  ## cpt.mean----
  ## Task: Nile Data----

# Multiple Changepoints ----
  ## cpt.var----
  ## cpt.meanvar----
  ## Tasks----
  ## How many changes?----
  ## cpt.np----
  ## Tasks----

# Checking Assumptions ----
## Segment check----
## Residual check----
## Task----

# Consolidating Task ----