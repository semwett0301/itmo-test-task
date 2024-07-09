rootProject.name = "itmo-test-task"

buildCache {
    local {
        isEnabled = true
        directory = File(rootDir, "build-cache")
    }
}
