// The currently selected tree object.
var selected_tree = null;

// Alert the properties of an object.
function alertObject(object)
{
	var msg = "";
	for (var p in object)
		msg += "object." + p + " = " + object[p] + "\n";
	alert(msg);
}

// Handle the select action.
function handleSelect(node, check, event)
{
	// Get the context objects
	var tree = node.target;
	var item = $(tree).jstree("get_selected")[0];
	
	// Deselect all from the other trees
	if (selected_tree && selected_tree != tree)
	{
		$(selected_tree).jstree("deselect_all");
	}
	selected_tree = tree;
	
	// Load the main content
	$.ajax(
		{
			url: "files/" + item.id + ".html",
			dataType: "html",
			success: function(msg)
			{
				$("#main").empty();
				$("#main").append(msg);
			}
		}
	);
}

// Select an object from a specific tree.
function selectObject(tree, item)
{
	// Select the right tab
	$(".tabs").tabs("select", tree);
	// Only select the desired node
	$("#" + tree).jstree("select_node", document.getElementById(item), true);
}

// Configure the environment.
$.jstree._themes = "styles/themes/";

// Initialize the trees.
$(".tree").jstree(
	{
		ui:
		{
			select_limit: 1
		},
		types:
		{
			types:
			{
image14:
{
icon:
{
image: "icons/image14.png"
}
},
image12:
{
icon:
{
image: "icons/image12.png"
}
},
image13:
{
icon:
{
image: "icons/image13.png"
}
},
image23:
{
icon:
{
image: "icons/image23.png"
}
},
image9:
{
icon:
{
image: "icons/image9.png"
}
},
image17:
{
icon:
{
image: "icons/image17.png"
}
},
image16:
{
icon:
{
image: "icons/image16.png"
}
},
image24:
{
icon:
{
image: "icons/image24.png"
}
},
image22:
{
icon:
{
image: "icons/image22.png"
}
},
image11:
{
icon:
{
image: "icons/image11.png"
}
},
image19:
{
icon:
{
image: "icons/image19.png"
}
},
image7:
{
icon:
{
image: "icons/image7.png"
}
},
image8:
{
icon:
{
image: "icons/image8.png"
}
},
image3:
{
icon:
{
image: "icons/image3.png"
}
},
image4:
{
icon:
{
image: "icons/image4.png"
}
},
image10:
{
icon:
{
image: "icons/image10.png"
}
},
image2:
{
icon:
{
image: "icons/image2.png"
}
},
image5:
{
icon:
{
image: "icons/image5.png"
}
},
image18:
{
icon:
{
image: "icons/image18.png"
}
},
image20:
{
icon:
{
image: "icons/image20.png"
}
},
image0:
{
icon:
{
image: "icons/image0.png"
}
},
image1:
{
icon:
{
image: "icons/image1.png"
}
},
image6:
{
icon:
{
image: "icons/image6.png"
}
},
image21:
{
icon:
{
image: "icons/image21.png"
}
},
image15:
{
icon:
{
image: "icons/image15.png"
}
}
			}
		},
		plugins: ["themes", "html_data", "ui", "types"]
	}
);
$(".tree").bind("select_node.jstree", handleSelect);

// Initialize the tabs.
$(".tabs").tabs();

// Initialize the layout.
$("#body").layout({ west: { size: 360, resizable: true }, center: { resizable: true }});