def call(String buildStatus) {
  // Default values
  def colorName = 'RED'
  def colorCode = '#FF0000'
  def subject = "#${env.BUILD_NUMBER} ${buildStatus}: QAC analysis Success. You can check the results in QAC Dashboard(http://13.124.166.12:8080)."
  def summary = "${subject}"

  // Override default values based on build status
  if (buildStatus == 'UNSTABLE') {
    color = 'YELLOW'
    colorCode = '#FFFF00'
    subject = "#${env.BUILD_NUMBER} ${buildStatus}: Analysis Unstable. You should be check pipeline."
  } else if (buildStatus == 'SUCCESS') {
    color = 'GREEN'
    colorCode = 'good'
  } else {
    color = 'RED'
    colorCode = 'danger'
    subject = "#${env.BUILD_NUMBER} ${buildStatus}: Analysis Failed. You should be check QAC Parsing error."
  }

  // Send notifications
  slackSend (color: colorCode, message: summary)
}