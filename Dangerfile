# Ignore inline messages which lay outside a diff's range of PR
github.dismiss_out_of_range_messages

# Make it more obvious that a PR is a work in progress and shouldn't be merged yet
warn("PR is classed as Work in Progress") if github.pr_title.include? "[WIP]"

# Android Lint
android_lint.gradle_modules = ["app", "presentation", "data", "web"]
android_lint.gradle_task = "lint"
android_lint.report_file = "build/reports/lint-results.xml"
android_lint.filtering = true
android_lint.lint(inline_mode: true)