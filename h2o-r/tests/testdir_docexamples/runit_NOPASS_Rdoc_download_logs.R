setwd(normalizePath(dirname(R.utils::commandArgs(asValues=TRUE)$"f")))
source('../h2o-runit.R')

test.rdoc_download_all_logs.golden <- function(H2Oserver) {
	

h2o.downloadAllLogs(H2Oserver, dirname = getwd(), filename = "h2o_logs.log")
file.info(paste(getwd(), "h2o_logs.log", sep = .Platform$file.sep))
file.remove(paste(getwd(), "h2o_logs.log", sep = .Platform$file.sep))

testEnd()
}

doTest("R Doc Download Logs", test.rdoc_download_all_logs.golden)

