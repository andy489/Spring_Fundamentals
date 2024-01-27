// ===============================
// variables
// ===============================
a = 5
console.log(a)
var a

let b
b = 4
console.log(b)

function example() {
    for (var i = 0; i < 5; i++) {
        console.log(i)
    }
    console.log(i)
}

console.log(i) // function scope for "var" variable
example()

// ===============================
// functions
// ===============================
named()

function named() {
    console.log("Named function called!")
}

// ===============================
// anonymous-functions
// ===============================
console.log(anon)
anon()
let anon = function() {
    console.log("Anon function assigned to let variable called!")
}

console.log(anon2)
anon2()
var anon2 = function() {
    console.log("Anon function assigned to var variable called!")
}