function personalBMI(name, age, weight, height) {
    let heightMeters = height / 100
    let BMI = Math.round(weight / (heightMeters * heightMeters))

    let status = "obese";
    if (BMI < 18.5) {
        status = "underweight"
    } else if (BMI < 25) {
        status = "normal"
    } else if (BMI < 30) {
        status = "overweight"
    }

    let person = {
        name: name,
        personalInfo: {
            age: age,
            weight: weight,
            height: height
        },
        BMI: BMI,
        status: status
    }

    if (status == "obese") {
        Object.assign(person, { recommendation: "admission required" });
    }

    return person
}

console.log(personalBMI("Peter", 29, 75, 182))
console.log(personalBMI("Honey Boo Boo", 9, 57, 137))