const selectList = document.getElementById("anno");
for(let i = 1950; i<2023; i++){
    let option = document.createElement("option");
    option.setAttribute("value", ""+i);
    option.setAttribute("name", "sceltaAnno");
    option.text = ""+i;
    selectList.appendChild(option);
}

