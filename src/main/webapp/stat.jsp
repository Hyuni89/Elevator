<%@ page import="java.util.ArrayList" %>
<%@ page import="com.loren.elevator.command.wrapper.ElevatorWrap" %>
<%@ page import="com.loren.elevator.model.Passenger" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html">
    <title>STAT</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/sockjs-client/sockjs.min.js"></script>
    <script src="/webjars/stomp-websocket/stomp.min.js"></script>
    <script src="./stat.js"></script>
</head>
<body>

<table id="building">
    <%
        int height = (int) request.getAttribute("height");
        int cnt = (int) request.getAttribute("cnt");
        boolean running = (boolean) request.getAttribute("run");
        ArrayList[] call = (ArrayList[]) request.getAttribute("call");
        List<ElevatorWrap> elevator = (List<ElevatorWrap>) request.getAttribute("elevator");

        if(!running) return;

        for(int i = height; i > 0; i--) {
    %>
    <tr class="even">
        <td>
            <%=i%>
        </td>
        <%
            for(int j = 0; j < cnt; j++) {
        %>
        <%
            if(elevator.get(j).getFloor() == i - 1) {
        %>
        <td class="elevator">
                <%
                    for(int k = 0; k < elevator.get(j).getPassengers().size(); k++) {
                        out.print(elevator.get(j).getPassengers().get(k));
                        if(elevator.get(j).getPassengers().size() - 1 != k) out.print(", ");
                    }
                } else {
            %>
        <td>
            <%
                }
            %>
        </td>
        <%
            }
        %>
        <td>
            <%
                for(int j = 0; j < call[i - 1].size(); j++) {
                    Passenger p = (Passenger) call[i - 1].get(j);
                    out.print(p.getId());
                    if(call[i - 1].size() - 1 != j) out.print(", ");
                }
            %>
        </td>
    </tr>
    <%
        }
    %>
    <tr class="even">
        <td>
            Status
        </td>
        <%
            for(int j = 0; j < cnt; j++) {
        %>
        <td>
            <%=elevator.get(j).getStatus()%>
        </td>
        <%
            }
        %>
    </tr>
</table>

</body>
</html>
