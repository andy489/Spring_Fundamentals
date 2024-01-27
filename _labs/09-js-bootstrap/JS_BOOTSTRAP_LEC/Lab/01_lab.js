let arr = [4, 1, 0, -4.2, 3.3, 19, 2, -0.3, -5]

function sortArray(arr, order) {
    let sortNums = (a, b) => a - b;

    if (order === "asc") {
        return arr.sort(sortNums)
    }

    return arr.sort(sortNums).reverse()
}

console.log(sortArray(arr, "asc"))
console.log(sortArray(arr, "desc"))