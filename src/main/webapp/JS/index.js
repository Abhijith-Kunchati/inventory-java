let xhr = new XMLHttpRequest();

let init = () => {
    displayItems();
    console.log(`JS initialized`);
}

let displayItems = () => {
    let itemsUrl = "http://localhost:8080/Inventory-Web/show-items";
    console.log(`Display function is called`);
    xhr.onload = () => {
        console.log(`Url loaded`);
        let itemArray = JSON.parse(xhr.responseText);
        let out = "";
        console.log(itemArray);
        for (let i = 0; i < itemArray.length; i++) {
            out += "<tr>" + "<th scope='row'>" + itemArray[i].itemID + "</th>" + "<td>" + itemArray[i].itemName + "</td>"+ "<td>" + itemArray[i].categoryID + "</td>" + "<td>" + itemArray[i].brandID + "</td>" + "<td>" + itemArray[i].availQuantity + "</td>" + "<td>" + itemArray[i].price + "</td>" + "</tr>";
        }
        document.getElementById("item-list").innerHTML = out;
    };
    xhr.open("GET", itemsUrl, true);
    xhr.send();
}

window.onload = () => {
    console.log(`Window loaded`);
    init();
}