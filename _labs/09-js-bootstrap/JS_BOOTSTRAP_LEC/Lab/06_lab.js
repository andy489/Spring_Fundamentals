let inputJuices1 = [
    'Orange => 2000',
    'Peach => 1432',
    'Banana => 450',
    'Peach => 600',
    'Strawberry => 549'
]

let inputJuices2 = [
    'Kiwi => 234',
    'Pear => 2345',
    'Watermelon => 3456',
    'Kiwi => 4567',
    'Pear => 5678',
    'Watermelon => 6789'
]
function getBottles(juices) {
    let output = {}
    let store = {}

    juices.forEach(juice => {
        let [item, quantity] = juice.split(' => ')

        if (item in store) {
            store[item] += Number(quantity)
        } else {
            store[item] = Number(quantity)
        }

        if (store[item] >= 1000) {
            if (item in output) {
                output[item] += Number(quantity)
            } else {
                output[item] = store[item]
            }
        }
    })

    for (let obj in output) {
        console.log(`${obj} => ${Math.floor(output[obj]/1000)}`)
    }
}

// getBottles(inputJuices1);
getBottles(inputJuices2);