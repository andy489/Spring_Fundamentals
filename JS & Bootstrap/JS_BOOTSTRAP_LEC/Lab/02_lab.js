function argumentsInfo() {
    let values = Object.values(arguments)

    let typeCounts = []

    for (const arg of values) {
        let type = typeof arg
        console.log(type + ": " + arg)

        let typeElement = typeCounts.find(el =>
            el[0] === type
        )

        if (typeElement) {
            typeElement[1] = typeElement[1] + 1
        } else {
            typeCounts.push([type, 1])
        }
    }

    typeCounts.sort((a, b) => b[1] - a[1])
        .forEach(el => console.log(el[0] + " = " + el[1]))
}

argumentsInfo('cat', 42, 43, function() { 'Hello world!' })