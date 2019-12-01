import 'package:flutter/material.dart';

void main() => runApp(new MaterialApp(home: new Home()));

class Home extends StatefulWidget {
  @override
  State createState() => _HomeState();
}

class _HomeState extends State<Home> {
  @override
  Widget build(BuildContext context) {
    return new Scaffold(
        appBar: new AppBar(
          title: new Text("Drag & Drop"),
        ),
        body: Builder(
            builder: (context) => Center(
                    child: Column(
                  mainAxisSize: MainAxisSize.min,
                  children: <Widget>[
                    Padding(
                      padding: const EdgeInsets.all(24.0),
                      child: buildDragTarget(),
                    ),
                    Padding(
                      padding: const EdgeInsets.all(24.0),
                      child:
                          buildDraggable(context, "Draggable", Icons.filter_1),
                    ),
                  ],
                ))));
  }

  Draggable buildDraggable(
          BuildContext context, String name, IconData childIcon) =>
      new Draggable(
        data: "I'm $name!",
        onDragStarted: () {
          print("DEBUG: Draggable.onDragStarted:");

          Scaffold.of(context).showSnackBar(new SnackBar(
            content: new Text("onDragStarted!"),
            duration: Duration(seconds: 1),
          ));
        },
        onDraggableCanceled: (velocity, offset) {
          print(
              "DEBUG: Draggable.onDraggableCanceled: velocity: $velocity, offset: $offset");

          Scaffold.of(context).showSnackBar(new SnackBar(
            content: new Text("onDraggableCanceled!"),
            duration: Duration(seconds: 1),
          ));
        },
        onDragCompleted: () {
          print("DEBUG: Draggable.onDragCompleted:");

          Scaffold.of(context).showSnackBar(new SnackBar(
            content: new Text("onDragCompleted!"),
            duration: Duration(seconds: 1),
          ));
        },
        onDragEnd: (details) {
          print("DEBUG: Draggable.onDragEnd: details: $details");

          Scaffold.of(context).showSnackBar(new SnackBar(
            content: new Text("onDragEnd!"),
            duration: Duration(seconds: 1),
          ));
        },
        child: Icon(
          childIcon,
          size: 90,
        ),
        feedback: Icon(
          Icons.android,
          size: 90,
        ),
        childWhenDragging: Icon(
          Icons.flag,
          size: 90,
        ),
      );

  DragTarget buildDragTarget() {
    BuildContext buildContext;
    return new DragTarget(
      builder: (context, candidateData, rejectedData) {
        print(
            "DEBUG: DragTarget.builder: candidateData: $candidateData, rejectedData: $rejectedData");

        buildContext = context;

        final MaterialColor color =
            candidateData.isEmpty ? Colors.red : Colors.blue;
        return new Container(
          width: 90,
          height: 90,
          color: color,
        );
      },
      onWillAccept: (data) {
        print("DEBUG: DragTarget.onWillAccept: data: $data");

        Scaffold.of(buildContext).showSnackBar(new SnackBar(
          content: new Text("onAccept!"),
          duration: Duration(seconds: 1),
        ));
        return true;
      },
      onAccept: (data) {
        print("DEBUG: DragTarget.onAccept: data: $data");

        Scaffold.of(buildContext).showSnackBar(new SnackBar(
          content: new Text("onAccept!"),
          duration: Duration(seconds: 1),
        ));
      },
      onLeave: (data) {
        print("DEBUG: DragTarget.onLeave: data: $data");

        Scaffold.of(buildContext).showSnackBar(new SnackBar(
          content: new Text("onLeave!"),
          duration: Duration(seconds: 1),
        ));
      },
    );
  }
}
