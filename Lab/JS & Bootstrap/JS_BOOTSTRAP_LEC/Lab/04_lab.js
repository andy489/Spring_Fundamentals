let heroes1 = [
    'Isacc / 25 / Apple, GravityGun',
    'Derek / 12 / BarrelVest, DestructionSword',
    'Hes / 1 / Desolator, Sentinel, Antara'
]

let heroes2 = ['Jake / 1000 / Gauss, HolidayGrenade']

function heroicInventory(arr) {
    return JSON.stringify(arr.map(str => {
        let tokens = str.split(" / ")

        return {
            name: tokens[0],
            level: +tokens[1],
            items: tokens[2].split(", ")
        }
    }))
}


console.log(heroicInventory(heroes1))
console.log(heroicInventory(heroes2))