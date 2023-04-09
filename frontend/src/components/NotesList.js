import Note from './Note';
import AddNote from './AddNote';
import { useState, useEffect } from 'react';
const NotesList = ({
	handleAddNote,
	handleDeleteNote,
  handleEditNote
}) => {
	const [notes, setNotes] = useState([]);
	useEffect(() => {
		fetch("api/v1/notes", {
		  headers: {
			"Content-Type": "application/json",
		  },
		  method: "get",
		})
		  .then((response) => {
			if (response.status === 200) return response.json();
		  })
		  .then((notesData) => {
			setNotes(notesData);
		  });
	  }, []);

	return (
		<div className='notes-list'>
			{notes.map((note) => (
				<Note
					id={note.id}
					content={note.content}
					handleDeleteNote={handleDeleteNote}

				/>
			))}
			<AddNote handleAddNote={handleAddNote} />
		</div>
	);
};

export default NotesList;