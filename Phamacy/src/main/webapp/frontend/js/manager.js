const product = [
  {
    img: "../img/shopList/1.png",
    condi: "New",
    name: "Digital Stethoscope",
    price: "$149.00",
  },
  {
    img: "../img/shopList/2.png",
    condi: "New",
    name: "Digital Stethoscope",
    price: "$149.00",
  },
  {
    img: "../img/shopList/3.png",
    condi: "New",
    name: "Digital Stethoscope",
    price: "$149.00",
  },
  {
    img: "../img/shopList/4.png",
    condi: "New",
    name: "Digital Stethoscope",
    price: "$149.00",
  },
  {
    img: "../img/shopList/5.png",
    condi: "New",
    name: "Digital Stethoscope",
    price: "$149.00",
  },
  {
    img: "../img/shopList/6.png",
    condi: "New",
    name: "Digital Stethoscope",
    price: "$149.00",
  },
  {
    img: "../img/shopList/7.png",
    condi: "New",
    name: "Digital Stethoscope",
    price: "$149.00",
  },
  {
    img: "../img/shopList/8.png",
    condi: "New",
    name: "Digital Stethoscope",
    price: "$149.00",
  },
  {
    img: "../img/shopList/9.png",
    condi: "New",
    name: "Digital Stethoscope",
    price: "$149.00",
  },
  {
    img: "../img/shopList/10.png",
    condi: "New",
    name: "Digital Stethoscope",
    price: "$149.00",
  },
  {
    img: "../img/shopList/11.png",
    condi: "New",
    name: "Digital Stethoscope",
    price: "$149.00",
  },
  {
    img: "../img/shopList/12.png",
    condi: "New",
    name: "Digital Stethoscope",
    price: "$149.00",
  },
  {
    img: "../img/shopList/13.png",
    condi: "New",
    name: "Digital Stethoscope",
    price: "$149.00",
  },
  {
    img: "../img/shopList/14.png",
    condi: "New",
    name: "Digital Stethoscope",
    price: "$149.00",
  },
];
$(document).ready(function () {
  // Activate tooltip
  $('[data-toggle="tooltip"]').tooltip();

  // Select/Deselect checkboxes
  var checkbox = $('table tbody input[type="checkbox"]');
  $("#selectAll").click(function () {
    if (this.checked) {
      checkbox.each(function () {
        this.checked = true;
      });
    } else {
      checkbox.each(function () {
        this.checked = false;
      });
    }
  });
  checkbox.click(function () {
    if (!this.checked) {
      $("#selectAll").prop("checked", false);
    }
  });
});
function renderAdmin() {
  html = "";

  const content = product.map((item, index) => {
    html += "<tr>";
    html += "<td>";
    html += '<span class="custom-checkbox">';
    html +=
      '<input type="checkbox" id="checkbox1" name="options[]" value="1"><label for="checkbox1"></label>';
    html += "</span>";
    html += "</td>";
    html += "<td>" + index + "</td";
    html += "<td>" + item.name + "</td";
    html += '<td><img src="' + item.img + '"></td>';
    html += "<td>" + item.price + "</td>";
    html +=
      '<td><a href="#editEmployeeModal"  class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a><a href="#deleteEmployeeModal" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a></td>';
    html += "</tr>";
    console.log(html);
    return html;
  });
  document.getElementById("adContent").innerHTML = html;
}
renderAdmin();
