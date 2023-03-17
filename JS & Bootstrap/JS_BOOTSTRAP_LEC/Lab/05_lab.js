let employees = [
    '{"name":"Pesho","position":"Promenliva","salary":100000}',
    '{"name":"Teo","position":"Lecturer","salary":1000}',
    '{"name":"Georgi","position":"Lecturer","salary":1000}'
]

function td(property) {
    return "<td>" + `${escapeHTML(property)}` + "</td>"
}

function escapeHTML(value) {
    return value
        .toString()
        .replace(/&/g, '&amp;')
        .replace(/</g, '&lt;')
        .replace(/>/g, '&gt;')
        .replace(/"/g, '&quot;')
        .replace(/'/g, '&#39;');
}

function toHtmlTable(arr) {
    let result = "<table>"

    arr.forEach(stringElement => {
        result += "<tr>"
        let currentEmpObj = JSON.parse(stringElement)
        result += td(currentEmpObj.name)
        result += td(currentEmpObj.position)
        result += td(currentEmpObj.salary)
        result += "</tr>"
    });

    result += "</table>"
    return result
}

console.log(toHtmlTable(employees))