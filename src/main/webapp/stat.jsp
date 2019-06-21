<%@ page import="java.util.List" %>
<%@ page import="com.loren.elevator.command.wrapper.ElevatorWrap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.loren.elevator.model.Passenger" %>
<%@ page language="java" contentType="text/html" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html">
    <title>STAT</title>
    <link rel="stylesheet", type="text/css" href="style.css">
</head>
<body>

<table>
    <%
        int height = (int) request.getAttribute("height");
        int cnt = (int) request.getAttribute("cnt");
        int hit = (int) request.getAttribute("hit");
        ArrayList[] call = (ArrayList[]) request.getAttribute("call");
        List<ElevatorWrap> elevator = (List<ElevatorWrap>) request.getAttribute("elevator");

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
        <td style="background-color: lightgreen">
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
<div>
    hitCount   [<%=hit%>]<br>
</div>
<%
    response.setIntHeader("Refresh", 0);
%>
</body>
</html>
