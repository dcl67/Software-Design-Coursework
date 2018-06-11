/*
 * SimpleMazeGame.java
 * Copyright (c) 2008, Drexel University.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the Drexel University nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY DREXEL UNIVERSITY ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL DREXEL UNIVERSITY BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package maze;

import maze.ui.MazeViewer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * 
 * @author Sunny
 * @version 1.0
 * @since 1.0
 */
public class SimpleMazeGame
{
	/**
	 * Creates a small maze.
	 */
	public static Maze createMaze()
	{
		
		Maze maze = new Maze();
		//System.out.println("The maze does not have any rooms yet!");
		Room r0 = new Room(0);
		Room r1 = new Room(1);
		Door d1 = new Door(r0,r1);
		d1.setOpen(false);
		maze.addRoom(r0);
		maze.addRoom(r1);
		r0.setSide(Direction.North, new Wall());
		r0.setSide(Direction.East, d1);
		r0.setSide(Direction.South, new Wall());
		r0.setSide(Direction.West, new Wall());
		r1.setSide(Direction.North, new Wall());
		r1.setSide(Direction.East, new Wall());
		r1.setSide(Direction.South, new Wall());
		r1.setSide(Direction.West, d1);

		return maze;
	}

	public static Maze loadMaze(final String path)
	{
		//Pre-defined array, not the best practice
		String[] commands = new String[31]; 
		String[] splitted = new String[100];
		String[] splitted2= new String[31];
		//Better practice: arraylists
		List<Room> rooms = new ArrayList<Room>();
		List<Door> doors = new ArrayList<Door>();
		int i=0, roomCount=0,doorCount=0;
		String north=null, south=null, east=null, west=null;
		char doorName;
		Maze maze = new Maze();
		//System.out.println("Please load a maze from the file!");
		try {
			BufferedReader file = new BufferedReader(new FileReader(path)); //file input
			for(String line=file.readLine(); line!=null; line=file.readLine()){
				commands[i]=line; //save commands to a local variable
				i++;
			}
			file.close();
		}
		
		//exception handling
		catch (FileNotFoundException e) {
			System.out.println(e);
		}
		catch(IOException e){
			System.out.println(e);
		}
		
		/*//Debugging purposes
		for(int x=0;x<commands.length;++x){
			if(commands[x]!=null){
				System.out.println(commands[x]);
			}
		}*/

		for(int z=0;z<commands.length;z++){ //for all commands
			if(commands[z]!=null){ //ignore null instances within array 
				//System.out.println(commands[z]);
				splitted=commands[z].toString().split("\\s+"); //split row of commands up
				if("room".equals(splitted[0])&&(splitted[0]!=null)){ //instructions for creating rooms
					north=splitted[2];
					south=splitted[3];
					east=splitted[4];
					west=splitted[5];
					Room bleh = new Room(Integer.parseInt(splitted[1]));
					rooms.add(bleh);
					maze.addRoom(bleh);
				}
				else if("door".equals(splitted[0])&&(splitted[0]!=null)){ //instructions for creating doors
					//north=splitted[2];
					//south=splitted[3];
					//east=splitted[4];
					//west=splitted[5];
					int room1 = Integer.parseInt(splitted[2]);
					int room2 = Integer.parseInt(splitted[3]);
					boolean hodor = ("close".equals(splitted[4]))?false:true;
					doors.add(newDoor(rooms.get(room1),rooms.get(room2),hodor));
				}
				else{
					System.out.println("else condition");
				}
			}
		}

		for(int w=0;w<commands.length;w++){
			if(commands[w]!=null){ //ignore null instances within array 
				//System.out.println(commands[w]);
				splitted2=commands[w].toString().split("\\s+"); //split row of commands up
				if("room".equals(splitted2[0])&&(splitted2[0]!=null)){
					Room roomList=rooms.get(roomCount);
					roomCount++;
					System.out.println(commands[w]);
					System.out.println(splitted2[3].charAt(0));
					//for (Room list: rooms){
						if("wall".equals(splitted2[2])&&(splitted2[2]!=null)){
							roomList.setSide(Direction.North, new Wall());
						}
						else if((splitted2[2].charAt(0)=='d')&&(splitted2[2]!=null)){
							System.out.println("Hey, I found a door in the north!");
							doorName=splitted2[2].charAt(1);
							roomList.setSide(Direction.North, doors.get(Character.getNumericValue(doorName)));
						}
						else{
							//System.out.println(rooms.get(Integer.parseInt(splitted2[2])));
							roomList.setSide(Direction.North, rooms.get(Integer.parseInt(splitted2[2])));
						}
						if("wall".equals(splitted2[3])&&(splitted2[3]!=null)){
							roomList.setSide(Direction.South, new Wall());
						}
						else if((splitted2[3].charAt(0)=='d')&&(splitted2[3]!=null)){
							System.out.println("Hey, I found a door in the west!");
							doorName=splitted2[3].charAt(1);
							System.out.print(doorName);
							roomList.setSide(Direction.South, doors.get(Character.getNumericValue(doorName)));
						}
						else{
							System.out.println(rooms.get(Integer.parseInt(splitted2[3])));
							roomList.setSide(Direction.South, rooms.get(Integer.parseInt(splitted2[3])));
						}
						if("wall".equals(splitted2[4])&&(splitted2[4]!=null)){
							roomList.setSide(Direction.East, new Wall());
						}
						else if((splitted2[4].charAt(0)=='d')&&(splitted2[4]!=null)){
							System.out.println("Hey, I found a door in the west!");
							doorName=splitted2[4].charAt(1);
							roomList.setSide(Direction.East, doors.get(Character.getNumericValue(doorName)));
						}
						else{
							System.out.println(rooms.get(Integer.parseInt(splitted2[4])));
							roomList.setSide(Direction.East, rooms.get(Integer.parseInt(splitted2[4])));
						}
						if("wall".equals(splitted2[5])&&(splitted2[5]!=null)){
							roomList.setSide(Direction.West, new Wall());
						}
						else if((splitted2[5].charAt(0)=='d')&&(splitted2[5]!=null)){
							System.out.println("Hey, I found a door in the west!");
							doorName=splitted2[5].charAt(1);
							roomList.setSide(Direction.West, doors.get(Character.getNumericValue(doorName)));
						}
						else{
							System.out.println(rooms.get(Integer.parseInt(splitted2[5])));
							roomList.setSide(Direction.West, rooms.get(Integer.parseInt(splitted2[5])));
						}
					}
				}
			} //End for

		return maze;
	}
	
	private static Door newDoor(Room room1, Room room2, boolean open){
		Door door = new Door(room1,room2);
		door.setOpen(open);
		
		return door;
	}


	public static void main(String[] args)
	{
		//Maze maze = createMaze(); /*Comment out to load a maze from a file*/
		if(args.length==0){
			Maze maze = createMaze();
			maze.setCurrentRoom(0);
		    MazeViewer viewer = new MazeViewer(maze);
		    viewer.run();
		}
		if(args.length==1){
			String fileInput=args[0];
			Maze maze = loadMaze(fileInput); /*Comment out to generate a pre-defined maze*/
			maze.setCurrentRoom(0);
		    MazeViewer viewer = new MazeViewer(maze);
		    viewer.run();
		}
		else{
			System.out.print("Too many inputs, check input and try again!");
		}
	}
}
