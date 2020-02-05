def call(String buildStatus) {
  // Default values
  def colorName = 'RED'
  def colorCode = '#FF0000'
  def subject = "#${env.BUILD_NUMBER} ${buildStatus}: QAC analysis Success. You can check the results in QAC Dashboard http://13.124.166.12:8080."
  def summary = "${subject} (${env.BUILD_URL})"
  def blocks = [
		{
			"type": "section",
			"text": {
				"type": "mrkdwn",
				"text": "<http://13.124.166.12:8080|QAC Board>"
			}
		}
	]
  // Override default values based on build status
  if (buildStatus == 'UNSTABLE') {
    color = 'YELLOW'
    colorCode = '#FFFF00'
    subject = "#${env.BUILD_NUMBER} ${buildStatus}: Analysis Unstable. You should be check Jenkins."
  } else if (buildStatus == 'SUCCESS') {
    color = 'GREEN'
    colorCode = '#00FF00'
  } else {
    color = 'RED'
    colorCode = '#FF0000'
    subject = "#${env.BUILD_NUMBER} ${buildStatus}: Analysis Failed. You should be check Jenkins."
  }

  // Send notifications
  slackSend (color: colorCode, message: summary, blocks=blocks)
}